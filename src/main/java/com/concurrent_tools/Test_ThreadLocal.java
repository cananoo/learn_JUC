package com.concurrent_tools;


/**
 * ThreadLocal
 */
public class Test_ThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(() -> 0);

        new Thread(()->{
            Integer value = integerThreadLocal.get();
            int result = value + 1;
            integerThreadLocal.set(result);
            System.out.println(integerThreadLocal.get());
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(integerThreadLocal.get()); //主线程仍然为0


    }
}
