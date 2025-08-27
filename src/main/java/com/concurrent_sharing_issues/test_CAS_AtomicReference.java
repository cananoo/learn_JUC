package com.concurrent_sharing_issues;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS_带版本的AtomicReference-AtomicStampedReference
 */
public class test_CAS_AtomicReference {
    public static void main(String[] args) throws InterruptedException {
        Account_CAS2 account = new Account_CAS2(100);
        AtomicStampedReference<Account_CAS2> aF = new AtomicStampedReference<>(account,0);
        Account_CAS2 prev = aF.getReference();
        int stamp = aF.getStamp();

        new Thread(()->{
            Account_CAS2 prev_thread = aF.getReference();
            int stamp_thread = aF.getStamp();
            aF.compareAndSet(prev_thread,new Account_CAS2(50),stamp_thread,stamp_thread+1);
        }).start();

        Thread.sleep(1000);

        new Thread(()->{
            Account_CAS2 prev_thread = aF.getReference();
            int stamp_thread = aF.getStamp();
            aF.compareAndSet(prev_thread,new Account_CAS2(100),stamp_thread,stamp_thread+1);
        }).start();

        Thread.sleep(1000);

        //测试是否解决了ABA问题
        boolean b = aF.compareAndSet(prev, new Account_CAS2(100), stamp, stamp + 1);
        System.out.println(b ? "修改成功" : "修改失败");


    }
}

class Account_CAS2{
    private int coins;

    public Account_CAS2(int coins) {
        this.coins = coins;
    }


}
