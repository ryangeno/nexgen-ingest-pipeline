package com.cloudera.sa.nexgen.ingest;

import com.cloudera.sa.nexgen.avro.Active_E_RAB_Number_15m;
import org.apache.avro.Schema;
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
import org.apache.crunch.io.avro.AvroFileTarget;
import org.apache.crunch.lib.SecondarySort;
import org.apache.crunch.types.avro.Avros;
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

    public static PCollection<Active_E_RAB_Number_15m> doWorkSon(final PTable<String, Pair<String, String>> joinedConfigRawStatsTable) {
        final Schema statsSchema = new Schema.Parser().parse("{\n" +
                "\t\"type\" : \"record\",\n" +
                "\t\"name\" : \"Active_E_RAB_Number_15m\",\n" +
                "\t\"fields\" : [\n" +
                "\t\t{\"name\" : \"MMEName\", \"type\" : [\"string\", \"null\"]},\n" +
                "\t\t{\"name\" : \"SWRelease\", \"type\" : [\"string\", \"null\"]},\n" +
                "\t\t{\"name\" : \"ENodeBId\", \"type\" : [\"int\", \"null\"]},\n" +
                "\t\t{\"name\" : \"ENodeBName\", \"type\" : [\"string\", \"null\"]},\n" +
                "\t\t{\"name\" : \"RecordTimestamp\", \"type\" : [\"string\", \"null\"]},\n" +
                "\t\t{\"name\" : \"CNum\", \"type\" : [\"string\", \"null\"]},\n" +
                "\t\t{\"name\" : \"QCI\", \"type\" : [\"string\", \"null\"]},\n" +
                "        {\"name\" : \"UsageNbrAvg\", \"type\" : [\"float\", \"null\"]},\n" +
                "\t\t{\"name\" : \"UsageNbrMax\", \"type\" : [\"float\", \"null\"]},\n" +
                "\t\t{\"name\" : \"UsageNbrTot\", \"type\" : [\"float\", \"null\"]},\n" +
                "\t\t{\"name\" : \"UsageNbrCnt\", \"type\" : [\"int\", \"null\"]}\n" +
                "\t]\n" +
                "}");

        return joinedConfigRawStatsTable.parallelDo(
                new DoFn<Pair<String, Pair<String, String>>, Active_E_RAB_Number_15m>() {
                    @Override
                    public void process(Pair<String, Pair<String, String>> rawStats, Emitter<Active_E_RAB_Number_15m> emitter) {

                        String configFields[] = rawStats.second().first().split(",");
                        String schemaName = configFields[0];
                        String addToSchemaFlag[] = configFields[1].split("|");
                        String fieldName[] = configFields[2].split("|");
                        String statsFields[] = rawStats.second().second().split(",");

                        Active_E_RAB_Number_15m statsRecord = new Active_E_RAB_Number_15m();
                        for (int i = 0; i < statsFields.length; i++) {
                            if(addToSchemaFlag[i].equals("Y")) {
                                statsRecord.put(fieldName[i], statsFields[i]);
                            }
                        }

                        emitter.emit(statsRecord);
                    }
                }, Avros.specifics(Active_E_RAB_Number_15m.class)
        );
    }

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
        PTable<String, Pair<Long, String>> configTable = configFile.parallelDo(
            new DoFn<String, Pair<String, Pair<Long, String>>>() {
                @Override
                public void process(String input, Emitter<Pair<String, Pair<Long, String>>> emitter) {
                    String[] configFields = input.split(",", -1);
                    if(!configFields[3].isEmpty()) {
                        String key = configFields[0] // network
                                + "," + configFields[1] // provider
                                + "," + configFields[2]; // group
                        //                            + "," + configFields[8]; // version

                        Long metricPos = Long.parseLong(configFields[10]); // metric position

                        String schemaName = configFields[12]; // table name
                        String metricLoadFlag = configFields[9]; // metric load flag

                        String metricName;
                        if(metricLoadFlag.equals("Y")) {
                            metricName = configFields[13]; // metric name
                        } else {
                            metricName = "None";
                        }

                        emitter.emit(Pair.of(key, Pair.of(metricPos, schemaName + "," + metricLoadFlag + "," + metricName)));
                    }
                }
            }, Writables.tableOf(Writables.strings(), Writables.pairs(Writables.longs(), Writables.strings())));


        // create a keyed stats table
        PTable<String, String> rawStatsTable = rawStatsFiles.parallelDo(
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


        // flatten config field names
        PTable<String, String> flattenedConfigTable = SecondarySort.sortAndApply(configTable,
                new DoFn<Pair<String, Iterable<Pair<Long, String>>>, Pair<String, String>>() {
                    @Override
                    public void process(Pair<String, Iterable<Pair<Long, String>>> sortedConfigs, Emitter<Pair<String, String>> emitter) {
                        Iterator<Pair<Long, String>> configs = sortedConfigs.second().iterator();
                        String tableName = null;
                        StringBuilder concatMetricLoadFlag = new StringBuilder();
                        StringBuilder concatMetricName = new StringBuilder();
                        while (configs.hasNext()) {
                            String splits[] = configs.next().second().split(",");
                            tableName = splits[0];
                            String metricLoadFlag = splits[1];
                            String metricName = splits[2];
                            concatMetricName.append(metricName + "|");
                            concatMetricLoadFlag.append(metricLoadFlag + "|");
                        }
                        emitter.emit(Pair.of(sortedConfigs.first(), tableName + "," + concatMetricLoadFlag.toString().substring(0, concatMetricLoadFlag.length()-1) + "," + concatMetricName.toString().substring(0, concatMetricName.length()-1)));
                    }
                }, Writables.tableOf(Writables.strings(), Writables.strings()));

        // join the raw stats and config tables, group by key
//        PGroupedTable<String, Pair<String, String>> joinedConfigRawStatsTable = parsedConfigTable.join(keyedRawStatsTable).groupByKey();
        PTable<String, Pair<String, String>> joinedConfigRawStatsTable = flattenedConfigTable.join(rawStatsTable);



        // group stats by output dir
        PCollection<Active_E_RAB_Number_15m> stats = doWorkSon(joinedConfigRawStatsTable);


//        pipeline.writeTextFile(join, outputPath);
//        pipeline.write(stats, new AvroPathPerKeyTarget("hdfs://nameservice1/projects/netiq/dev/data/work/test_rg"),WriteMode.OVERWRITE)
//        stats.groupByKey().write(new AvroPathPerKeyTarget("hdfs://nameservice1/projects/netiq/dev/data/work/test_rg"),WriteMode.OVERWRITE);
//        joinedConfigRawStatsTable.write(At.textFile(outputPath), WriteMode.OVERWRITE);
        pipeline.write(stats, new AvroFileTarget("hdfs://nameservice1/projects/netiq/dev/data/work/test_rg"),WriteMode.OVERWRITE);


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



















