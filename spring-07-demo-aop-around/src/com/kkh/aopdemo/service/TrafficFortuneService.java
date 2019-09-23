package com.kkh.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
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

    public String getFortune(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("Major accident! Highway is closed!");
        }

        return this.getFortune();
    }
}
