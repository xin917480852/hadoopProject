package com.xiaoxin.hdfs;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * 用流的方式来操作hdfs上的文件
 * 可以实现读取指定偏移量范围的数据
 * @author 谢榕新
 * @desc java用流的方式来操作hdfs上的文件
 *
 */
public class HdfsStreamAccess {

    FileSystem fs = null;
    Configuration conf = null;

    @Before
    public void init() throws Exception{

        conf = new Configuration();
        //拿到一个文件系统操作的客户端实例对象
//		fs = FileSystem.get(conf);
        //可以直接传入 uri和用户身份
        fs = FileSystem.get(new URI("hdfs://hadoop1:9000"),conf,"root");
    }


    /**
     * 通过流的方式上传文件到hdfs
     * @throws Exception
     */
    @Test
    public void testUpload() throws Exception {

        FSDataOutputStream outputStream = fs.create(new Path("/teststreamhadoop.txt.copy"), true);

        FileInputStream inputStream = new FileInputStream("d:/teststreamhadoop.txt");

        IOUtils.copy(inputStream, outputStream);
    }


    /**
     * 通过流的方式获取hdfs上数据
     * @throws Exception
     */
    @Test
    public void testDownLoad() throws Exception {

        FSDataInputStream inputStream = fs.open(new Path("/teststreamhadoop.txt.copy"));

        FileOutputStream outputStream = new FileOutputStream("e:/teststreamhadoop.txt");

        IOUtils.copy(inputStream, outputStream);

    }


    @Test
    public void testRandomAccess() throws Exception{

        FSDataInputStream inputStream = fs.open(new Path("/teststreamhadoop.txt.copy"));

        inputStream.seek(4);

        FileOutputStream outputStream = new FileOutputStream("e:/teststreamhadoop.txt_4");

        IOUtils.copy(inputStream, outputStream);


    }



    /**
     * 显示hdfs上文件的内容
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Test
    public void testCat() throws IllegalArgumentException, IOException {

        FSDataInputStream in = fs.open(new Path("/teststreamhadoop.txt.copy"));

        IOUtils.copy(in, System.out);

//		IOUtils.copyBytes(in, System.out, 1024);
    }

}

