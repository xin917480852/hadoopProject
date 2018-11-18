package com.xiaoxin.rpc.client;

import com.xiaoxin.rpc.protocol.IUserLoginService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

public class UserLoginAction {
    public static void main(String[] args) throws Exception {
        IUserLoginService userLoginService = RPC.getProxy(IUserLoginService.class, 100L,
                new InetSocketAddress("localhost", 8999), new Configuration());
        String login = userLoginService.login("juaner", "5201314");
        System.out.println(login);

    }
}
