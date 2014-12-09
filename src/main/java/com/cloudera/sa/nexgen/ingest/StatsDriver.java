package com.cloudera.sa.nexgen.ingest;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.MapFn;
import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.PGroupedTable;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.Target.WriteMode;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.At;
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
        if(args.length != 3) {
            System.err.println(args.length);
            System.err.println("Two and only two arguments are accepted.");
            System.err.println("Usage: " + this.getClass().getName() + " [generic options] input output");
            System.err.println();
            GenericOptionsParser.printGenericCommandUsage(System.err);
            return 1;
        }

        // Get raw stats input path and output paths
        final String rawStatsInputPath = args[0];
        final String configDriverInputPath = args[1];
        final String outputPath = args[2];

        // Create an instance of Pipeline by providing ClassName and the job configuration.
        final Pipeline pipeline = new MRPipeline(StatsDriver.class, new Configuration());

        // Read text files
        final PCollection<String> rawStatsFiles = pipeline.readTextFile(rawStatsInputPath);
        final PCollection<String> configFile = pipeline.readTextFile(configDriverInputPath);

        // create a keyed config table
        PTable<String, Pair<Long, String>> keyedConfigTable = configFile.parallelDo(
            new MapFn<String, Pair<String, Pair<Long, String>>>() {
                    @Override
                    public Pair<String, Pair<Long, String>> map(String input) {
                    String[] configFields = input.split(",");
                    String key = configFields[0] // customer
                            + "," + configFields[1] // provider
                            + "," + configFields[11]; // group
//                            + "," + configFields[8]; // version
                    Long metricPos = Long.parseLong(configFields[16]); // metric position
                    String value = configFields[7] // record length
                            + "," + configFields[14]; // metric name
                    return Pair.of(key, Pair.of(metricPos, value));
                }
            }, Writables.tableOf(Writables.strings(), Writables.pairs(Writables.longs(), Writables.strings())));

        // create a keyed stats table
        PTable<String, String> keyedRawStatsTable = rawStatsFiles.parallelDo(
                new MapFn<String, Pair<String, String>>() {
                    @Override
                    public Pair<String, String> map(String input) {
                        String[] rawStatsFields = input.split(",");
                        String key = "Samsung"
                                + "," + "Sprint"
                                + "," + rawStatsFields[0].replace("\"", ""); // group
//                                + "," + rawStatsFields[1]; // version
                        String value = key
                                + "," + input;
                        return Pair.of(key, value);
                    }
                }, Writables.tableOf(Writables.strings(), Writables.strings()));

        // load the Avro schema
        // TODO: read from HDFS
//        Schema statsSchema = new Schema.Parser().parse(new File("src/main/avro/min15_c_num_qci.avsc"));
        Pair<PCollection<String>, PCollection<String>> = x;

        // flatten config field names
        PTable<String, String> parsedConfigTable = SecondarySort.sortAndApply(keyedConfigTable,
                new DoFn<Pair<String, Iterable<Pair<Long, String>>>, Pair<String, String>>() {
                    @Override
                    public void process(Pair<String, Iterable<Pair<Long, String>>> sortedConfigs, Emitter<Pair<String, String>> emitter) {
                        Iterator<Pair<Long, String>> configs = sortedConfigs.second().iterator();
                        String recordLength = null;
                        StringBuilder fields = new StringBuilder();
                        while (configs.hasNext()) {
                            String splits[] = configs.next().second().split(",");
                            recordLength = splits[0];
                            String metricName = splits[1];
                            fields.append(metricName + "|");
                        }
                        emitter.emit(Pair.of(sortedConfigs.first(), recordLength + "," + fields.toString().substring(0, fields.length()-1)));
                    }
                }, Writables.tableOf(Writables.strings(), Writables.strings()));

        // join the raw stats and config tables, group by key
        PGroupedTable<String, Pair<String, String>> joinedConfigRawStatsTable = parsedConfigTable.join(keyedRawStatsTable).groupByKey();

        // group stats by output dir
//        PTable<String, PCollection<String>> parsedStats = joinedConfigRawStatsTable.parallelDo(
//                new DoFn<Pair<String, Iterable<Pair<String, String>>>, Pair<String, PCollection<String>>>() {
//                    @Override
//                    public void process(Pair<String, Iterable<Pair<String, String>>> keyedStats, Emitter<Pair<String, PCollection<String>>> emitter) {
//                        Iterator<Pair<String, String>> stats = keyedStats.second().iterator();
//                        StringBuilder fields = new StringBuilder();
//                        while (configs.hasNext()) {
//                            String fieldName = configs.next().second();
//                            fields.append(fieldName + "|");
//                        }
//                        emitter.emit(Pair.of(sortedConfigs.first(), fields.toString().substring(0, fields.length()-1)));
//                    }
//                }, Writables.tableOf(Writables.strings(), Writables.strings()));


//        pipeline.writeTextFile(join, outputPath);
        joinedConfigRawStatsTable.write(At.textFile(outputPath), WriteMode.OVERWRITE);



        // run the pipeline!
        PipelineResult result = pipeline.done();

        return result.succeeded() ? 0 : 1;

//        rootdir/A
//        (A, A some stuff)
//        (A, A others)
//        rootdir/B
//        (B, B stuff)
//avro specifics


    }

    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new StatsDriver(), args);
        System.exit(result);
    }


}



















