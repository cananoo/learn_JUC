package com.concurrent_tools;

public class Semaphore {

    public static void main(String[] args) {
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(3);

        for (int i = 0; i < 10; i++) {
            int a = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread -" + a + "running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                semaphore.release();
            }).start();
        }
    }
}
