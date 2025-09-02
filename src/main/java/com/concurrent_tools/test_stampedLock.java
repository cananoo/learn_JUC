package com.concurrent_tools;

import java.util.concurrent.locks.StampedLock;

public class test_stampedLock {
    private static int data = 10;
    public static void main(String[] args) {
        StampedLock lock = new StampedLock();

        long stamp = lock.tryOptimisticRead();

        boolean validate = lock.validate(stamp);

        System.out.println(validate ? "查询成功：" + data : "查询失败，已被修改");


    }
}
