package com.company;

import java.util.Random;

public class RandomTimer {
    private int maxWaitTime = 25000; // could be longer

    public RandomTimer(){
    }

    public RandomTimer(int maxWaitTime){
        this.maxWaitTime = maxWaitTime;
    }

    private void waitUntil(long timestamp) {
        long millis = timestamp - System.currentTimeMillis();
        // return immediately if time is already in the past
        if (millis <= 0)
            return;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public boolean waitToClockin(){
        Random randomNum = new Random();
        int waitTime = randomNum.nextInt(maxWaitTime);
        waitUntil(System.currentTimeMillis() + waitTime);

        return true;
    }
}
