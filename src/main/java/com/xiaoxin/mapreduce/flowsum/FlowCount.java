package com.xiaoxin.mapreduce.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCount {

    static class FlowCountMapper extends Mapper<LongWritable,Text, Text,FlowBean>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line =value.toString();
            String[] fields =line.split("\t");
            String phone=fields[1];
            long upFlow = Long.parseLong(fields[fields.length-3]);
            long dFlow = Long.parseLong(fields[fields.length-2]);
            context.write(new Text(phone),new FlowBean(upFlow,dFlow));
        }
    }

    static class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean>{
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            long sum_upFlow = 0;
            long sum_dFlow = 0;
            for(FlowBean bean:values){
                sum_upFlow+=bean.getUpFlow();
                sum_dFlow+=bean.getdFlow();
            }
            FlowBean flowBean = new FlowBean(sum_upFlow,sum_dFlow);
            context.write(key,flowBean);
        }
    }
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowCount.class);
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //指定job的输出结果所在目录
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);

    }

}
