package com.cloudera.sa.nexgen.ingest;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.MapFn;
import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.Target.WriteMode;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.text.TextFileTarget;
import org.apache.crunch.lib.Channels;
import org.apache.crunch.lib.SecondarySort;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.Serializable;
import java.util.Iterator;

//import org.apache.crunch.types.writable.Avros;


/**
 * Created by ryangeno on 11/29/14.
 */
public class StatsDriver extends Configured implements Tool, Serializable {

    public int run(String[] args) throws Exception {
        if(args.length != 4) {
            System.err.println(args.length);
            System.err.println("Four arguments are accepted.");
            System.err.println("Usage: " + this.getClass().getName() + " [generic options] input output");
            System.err.println();
            GenericOptionsParser.printGenericCommandUsage(System.err);
            return 1;
        }

        // Get raw stats input path and output paths
        final String rawStatsInputPath = args[0];
        final String configDriverInputPath = args[1];
        final String outputPath = args[2];
        final String groupingName = args[3];

        String tableName = groupingName.replace("\"", "").replaceAll("[^a-zA-Z0-9]", "_") + "_15m".toLowerCase();
        final String cleanGroupName = groupingName.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Create an instance of Pipeline by providing ClassName and the job configuration.
        final Pipeline pipeline = new MRPipeline(StatsDriver.class, new Configuration());

        // Read text files
        final PCollection<String> rawStatsFiles = pipeline.readTextFile(rawStatsInputPath);
        final PCollection<String> configFile = pipeline.readTextFile(configDriverInputPath);


        // create a keyed config table
        PTable<String, Pair<Long, String>> configTable = configFile.parallelDo(
                new DoFn<String, Pair<String, Pair<Long, String>>>() {
                    @Override
                    public void process(String input, Emitter<Pair<String, Pair<Long, String>>> emitter) {
                        String[] configFields = input.split(",", -1);
                        if(configFields[3].equals("TRUE")) {
                            String key = configFields[0] // network
                                    + "," + configFields[1] // provider
                                    + "," + configFields[4]; // group
                            //                            + "," + configFields[8]; // version

                            Long filePos = Long.parseLong(configFields[11]); // metric position
                            String targetTableName = configFields[5]; // table name
                            String metricLoadFlag = configFields[6]; // metric load flag
                            String recordLength = configFields[9]; // metric load flag

//                            String metricName;
//                            if(metricLoadFlag.equals("Y")) {
//                                metricName = configFields[13]; // metric name
//                            } else {
//                                metricName = "None";
//                            }

                            emitter.emit(Pair.of(key, Pair.of(filePos, targetTableName + "," + metricLoadFlag + "," + recordLength)));
                        }
                    }
                }, Writables.tableOf(Writables.strings(), Writables.pairs(Writables.longs(), Writables.strings())));


        // create a keyed stats table
        PTable<String, String> rawStatsTable = rawStatsFiles.parallelDo(
                new MapFn<String, Pair<String, String>>() {
                    @Override
                    public Pair<String, String> map(String input) {
                        String[] rawStatsFields = input.split(",", -1);
                        String key = "Samsung"
                                + "," + "Sprint"
                                + "," + rawStatsFields[0].replace("\"", ""); // group
//                                + "," + rawStatsFields[1]; // version
                        String value = input;
                        return Pair.of(key, value);
                    }
                }, Writables.tableOf(Writables.strings(), Writables.strings()));


        // flatten config field names
        PTable<String, String> flattenedConfigTable = SecondarySort.sortAndApply(configTable,
                new DoFn<Pair<String, Iterable<Pair<Long, String>>>, Pair<String, String>>() {
                    @Override
                    public void process(Pair<String, Iterable<Pair<Long, String>>> sortedConfigs, Emitter<Pair<String, String>> emitter) {
                        Iterator<Pair<Long, String>> configs = sortedConfigs.second().iterator();
                        String tableName = null;
                        StringBuilder concatMetricLoadFlag = new StringBuilder();
                        String recordLength = null;
//                        StringBuilder concatMetricName = new StringBuilder();
                        while (configs.hasNext()) {
                            String splits[] = configs.next().second().split(",");
                            tableName = splits[0];
                            String metricLoadFlag = splits[1];
                            recordLength = splits[2];
//                            String metricName = splits[2];
//                            concatMetricName.append(metricName + "|");
                            concatMetricLoadFlag.append(metricLoadFlag + "|");
                        }
                        emitter.emit(Pair.of(sortedConfigs.first(), tableName + "," + concatMetricLoadFlag.toString().substring(0, concatMetricLoadFlag.length()-1) + "," + recordLength));
                    }
                }, Writables.tableOf(Writables.strings(), Writables.strings()));


        // join the raw stats and config tables
        PTable<String, Pair<String, String>> joinedConfigRawStatsTable = flattenedConfigTable.join(rawStatsTable);

        // Validate raw data based on the config data
        PCollection<Pair<String, String>> errCheckedStats = joinedConfigRawStatsTable.parallelDo(
                new DoFn<Pair<String, Pair<String, String>>, Pair<String, String>>() {
                    @Override
                    public void process(Pair<String, Pair<String, String>> input, Emitter<Pair<String, String>> emitter) {
                        Long recordLength = Long.parseLong(input.second().first().split(",")[2]);
                        String data = input.second().second();
                        String[] fields = data.split(",");
                        String goodData = null;
                        String badData = null;

                        // Row length from the config and the actual row length in the data
                        if (recordLength == fields.length) {
                            // Good data
                            goodData = data;
                        }
                        else {
                            // Bad data
                            badData = data;
                        }
                        emitter.emit(Pair.of(input.second().first() + "\t" + goodData, badData));
                    }
                }, Writables.pairs(Writables.strings(), Writables.strings()));
//
        Pair<PCollection<String>, PCollection<String>> split = Channels.split(errCheckedStats);
        PCollection<String> goodData = split.first();
        PCollection<String> badData = split.second();

        // group stats by output dir
        PCollection<String> stats = goodData.parallelDo(
                new DoFn<String, String>() {

                    @Override
                    public void process(String input, Emitter<String> emitter) {

                        // Split left configs and right stats data
                        String configRecords = input.split("\t")[0];
                        String rawStatsRecords = input.split("\t")[1];

                        // get group from key
                        String groupType = rawStatsRecords.split(",")[0].replace("\"", "").replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

                        // set table and metric load flags
                        String configFields[] = configRecords.split(",");
//                        String tableName = configFields[0];
                        String metricLoadFlags[] = configFields[1].split("\\|");
//                        String fieldName[] = configFields[2].split("\\|");

                        // get raw stats
                        String rawStatsFields[] = rawStatsRecords.split(",");

                        StringBuilder statsRecord = new StringBuilder();
                        statsRecord.append("SJSLSM1" + ",");

                        if(groupType.equals(cleanGroupName)) {
                            for (int i = 0; i < rawStatsFields.length; i++) {
                                if(metricLoadFlags[i].equals("TRUE")) {
                                    statsRecord.append(rawStatsFields[i] + ",");
                                }
                            }

                            emitter.emit(statsRecord.toString().substring(0, statsRecord.length()-1));
                        };
                    }
                }, Writables.strings()
        );

        pipeline.write(stats, new TextFileTarget(outputPath + "/tmp_" + tableName.toLowerCase()), WriteMode.OVERWRITE);
        pipeline.write(badData, new TextFileTarget(outputPath + "/" + "bad_records"), WriteMode.APPEND);

        // run the pipeline!
        PipelineResult result = pipeline.done();

        return result.succeeded() ? 0 : 1;

    }

    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new StatsDriver(), args);
        System.exit(result);
    }


}



















