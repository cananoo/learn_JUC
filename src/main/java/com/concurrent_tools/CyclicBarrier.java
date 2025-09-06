package com.concurrent_tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * CyclicBarrier
 */
public class CyclicBarrier {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(2,
                ()->{
                    System.out.println("结束运行");
                });

        pool.submit(()->{
            System.out.println("Thread-1-running" );
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });

        pool.submit(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread-2-running" );
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });





    }
}
