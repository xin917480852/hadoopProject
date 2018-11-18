package com.xiaoxin.rpc.client;

import com.xiaoxin.rpc.protocol.ClientNamenodeProtocol;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

public class MyHdfsClient {

    public static void main(String[] args) throws Exception {
        ClientNamenodeProtocol namenode = RPC.getProxy(ClientNamenodeProtocol.class, 1L,
                new InetSocketAddress("localhost", 8889), new Configuration());
        String metaData = namenode.getMetaData("/juaner.mygirl");
        System.out.println(metaData);
    }
}
