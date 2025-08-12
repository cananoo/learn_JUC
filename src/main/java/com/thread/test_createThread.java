package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class test_createThread {
    public static void main(String[] args) {

        // method_1
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("running");
            }
        };
        t1.setName("t1");
        t1.start();



        //method_2
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }

        };

        Thread t2 = new Thread(runnable);
        t2.start();

        System.out.println("main running");

        //method_3
        Mycallable mycallable = new Mycallable();
        FutureTask<String> task = new FutureTask<>(mycallable);
        Thread t3 = new Thread(task,"t3");
        t3.start();

        try {
            String s = task.get();
            System.out.println(s);
        } catch (Exception e){
            e.printStackTrace();
        }
        
        System.out.println("end running");
    }


}

 class Mycallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() +" " + "call running";
    }
}