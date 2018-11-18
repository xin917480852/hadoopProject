package com.xiaoxin.rpc.service;

import com.xiaoxin.rpc.protocol.IUserLoginService;

public class UserLoginServiceImpl implements IUserLoginService {


    public String login(String name, String passwd) {

        return name + "  logged in successfully...";
    }




}
