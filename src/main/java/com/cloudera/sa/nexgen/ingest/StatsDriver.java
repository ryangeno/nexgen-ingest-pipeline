package com.cloudera.sa.nexgen.ingest;

import org.apache.crunch.*;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.Serializable;


/**
 * Created by ryangeno on 11/29/14.
 */
public class StatsDriver extends Configured implements Tool, Serializable {

    public int run(String[] args) throws Exception {
        if(args.length != 2) {
            System.err.println();
            System.err.println("Two and only two arguments are accepted.");
            System.err.println("Usage: " + this.getClass().getName() + " [generic options] input output");
            System.err.println();
            GenericOptionsParser.printGenericCommandUsage(System.err);
            return 1;
        }

        // Get raw stats input path and output paths
        final String rawStatsInputPath = args[0];
        final String outputPath = args[1];

        // Create an instance of Pipeline by providing ClassName and the job configuration.
        final Pipeline pipeline = new MRPipeline(StatsDriver.class, new Configuration());

        // Read a text file
        final PCollection<String> rawStatsFiles = pipeline.readTextFile(rawStatsInputPath);

        final PTable<String, String> test = getTable(rawStatsFiles);



        pipeline.writeTextFile(test, outputPath);

        // run the pipeline!
        PipelineResult result = pipeline.done();

        return result.succeeded() ? 0 : 1;




    }

    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new StatsDriver(), args);
        System.exit(result);
    }

    public PTable<String, String> getTable(PCollection<String> inputData) {
//        PTypeFamily tf = inputData.getTypeFamily();
        return inputData.parallelDo(new DoFn<String, Pair<String, String>>() {
            @Override
            public void process(String input, Emitter<Pair<String, String>> emitter) {
                String tableKey = input.split(",")[0];
                emitter.emit(Pair.of(tableKey, input));
            }
        }, Writables.tableOf(Writables.strings(), Writables.strings()));
    }


}
