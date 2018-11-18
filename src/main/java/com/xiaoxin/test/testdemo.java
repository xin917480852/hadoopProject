package com.xiaoxin.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testdemo {

    @Test
    public void test(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String sdate = simpleDateFormat.format(date);
        System.out.println(date);
        System.out.println(sdate);
    }



}
