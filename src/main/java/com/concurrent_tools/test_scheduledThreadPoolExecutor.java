package com.concurrent_tools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor
 */
public class test_scheduledThreadPoolExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        pool.scheduleAtFixedRate(()->{
            System.out.println(1);
        },1,1, TimeUnit.SECONDS);


        pool.scheduleWithFixedDelay(()->{
            System.out.println(1);
        },1,1, TimeUnit.SECONDS);

    }
}
