package com.kkh.aopdemo.service;

import java.util.concurrent.TimeUnit;

public class TrafficFortuneService {

    public String getFortune() {

        // simulate a dely
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // return a fortune
        return "Except heavy traffic this morning";
    }
}
