package com.concurrent_sharing_issues;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class test_CAS {
    public static void main(String[] args) throws InterruptedException {
       Account_CAS account = new Account_CAS(10000);
        System.out.println(account.getBalance());
        List<Thread> list = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(()->{
                account.withdraw(10);
            });
            list.add(thread);
        }
        for (Thread a : list){
            a.start();
        }
        for (Thread a : list){
            a.join();
        }
        System.out.println(account.getBalance());
    }
}

class Account_CAS{
    private AtomicInteger balance;

    public Account_CAS(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    public Integer getBalance(){
        return balance.get();
    }

    public void withdraw(int amount){
        while (true){
            int prev = balance.get();
            int next = prev - amount;
            if (balance.compareAndSet(prev,next)){
                break;
            }
            // 相当于balance.addAndGet(-1*amount);
        }
    }

}