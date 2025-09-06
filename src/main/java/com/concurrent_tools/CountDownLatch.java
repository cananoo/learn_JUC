package com.concurrent_tools;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLock 模拟游戏加载
 */
public class CountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(10);

        String[] per = new String[10];

        for (int i = 0; i < 10; i++) {
            int k = i;
            pool.submit(()->{
                Random random = new Random();
                for (int j = 0; j < 100; j++) {
                    try {
                        Thread.sleep(random.nextInt(150));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    per[k] = j + 1 + "%";
                    System.out.print("\r" + Arrays.toString(per));
                }
                countDownLatch.countDown();
            });
        }


        countDownLatch.await();
        System.out.println("\n" + "----游戏开始----");
        pool.shutdown();


    }
}
