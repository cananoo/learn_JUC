package com.concurrent_sharing_issues;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的使用
 * 三个线程分别打印a、b、c ，按abc的顺序打印5次
 */
public class test_reentrantLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition Home = lock.newCondition();


        Status status = new Status();
        status.setStatus(1);



        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    if (status.getStatus() == 1){
                        System.out.print("a");
                        status.setStatus(2);
                        Home.signalAll();
                    }else {
                        i--;
                        Home.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    if (status.getStatus() == 2){
                        System.out.print("b");
                        status.setStatus(3);
                        Home.signalAll();
                    }else {
                        i--;
                        Home.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    if (status.getStatus() == 3){
                        System.out.print("c");
                        status.setStatus(1);
                        Home.signalAll();
                    }else {
                        i--;
                        Home.await(); 
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "t3");


        t1.start();
        t2.start();
        t3.start();

    }



}

class Status{
    /**
     1-可打印a 2-可打印b 3-可打印c
     */
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}