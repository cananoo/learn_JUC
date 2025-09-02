package com.concurrent_tools;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 */
public class test_reentrantReadWriteLock {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock r = lock.readLock();
        ReentrantReadWriteLock.WriteLock w = lock.writeLock();

        new Thread(()->{
            r.lock();

            System.out.println("i am thread_1_r");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.unlock();


        }).start();

        new Thread(()->{
            r.lock();

            System.out.println("i am thread_2_r");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.unlock();

        }).start();


        new Thread(()->{
            w.lock();
            System.out.println("i am thread_1_w");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            w.unlock();

        }).start();


        new Thread(()->{
            w.lock();

            System.out.println("i am thread_1_w");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            w.unlock();

        }).start();


    }
}
