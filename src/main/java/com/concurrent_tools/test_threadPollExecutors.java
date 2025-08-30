package com.concurrent_tools;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPollExecutors
 */
public class test_threadPollExecutors {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 固定核心线程池
         */
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
           private AtomicInteger c = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"myName_" + c.getAndIncrement());
            }
        });

        pool.execute(()->{
            System.out.println(1);
        });

        pool.execute(()->{
            System.out.println(2);
        });

        /**
         * 全紧急线程池
         */
       Executors.newCachedThreadPool();

        /**
         * 单线程池
         */
        Executors.newSingleThreadExecutor();

        System.out.println("-------------------------------------------------");

        /**
         *  提交任务-submit
         */
        ExecutorService p = Executors.newFixedThreadPool(2);
        Future<String> future = p.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "ok";
            }
        });

        System.out.println(future.get());

        /**
         * 提交任务-invokeAll的使用
         */
        List<Future<Integer>> futures = p.invokeAll(Arrays.asList(
                () -> {
                    return 1;
                },
                () -> {
                    return 2;
                },
                () -> {
                    return 3;
                }
        ));
        for (Future<Integer> f :futures){
            System.out.println(f.get());
        }
    }
}
