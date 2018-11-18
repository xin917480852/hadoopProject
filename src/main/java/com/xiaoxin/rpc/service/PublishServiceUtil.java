package com.xiaoxin.rpc.service;
import com.xiaoxin.rpc.protocol.ClientNamenodeProtocol;
import com.xiaoxin.rpc.protocol.IUserLoginService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

public class PublishServiceUtil {

    public static void main(String[] args) throws Exception {
        Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost")
                .setPort(8889)
                .setProtocol(ClientNamenodeProtocol.class)
                .setInstance(new ClientNamenodeImp());

        Server server = builder.build();
        server.start();


        Builder builder2 = new RPC.Builder(new Configuration());
        builder2.setBindAddress("localhost")
                .setPort(8999)
                .setProtocol(IUserLoginService.class)
                .setInstance(new UserLoginServiceImpl());

        Server server2 = builder2.build();
        server2.start();






    }
}
