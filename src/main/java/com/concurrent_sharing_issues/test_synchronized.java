package com.concurrent_sharing_issues;

/**
 *  synchronized 解决共享问题
 */
public class test_synchronized {
    static int count = 0;
    static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (lock){
                        count++;
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5000; i++) {
                    synchronized (lock){
                        count--;
                    }
                }
            }
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);

    }
}
