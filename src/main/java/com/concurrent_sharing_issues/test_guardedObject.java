package com.concurrent_sharing_issues;




/**
 * 设计模式-保护性暂停
 */
public class test_guardedObject {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(()->{
            System.out.println(guardedObject.isCompleted);
            Object o = guardedObject.get(10000);
            System.out.println(guardedObject.isCompleted);
            System.out.println(!guardedObject.isCompleted?"获取失败":"获取成功"+o);
        },"t1").start();

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        guardedObject.complete(new Object());


    }
}

class GuardedObject{

   public Boolean isCompleted = false;
    private Object response;

    public void complete(Object response){
        synchronized (this){
            this.response = response;
            isCompleted = true;
            this.notifyAll();
        }

    }

    public Object get(long time){
        synchronized (this){
            long start = System.currentTimeMillis();
            long passTime = 0;
            while (!isCompleted){
                long waitTime = time - passTime;
                if (waitTime <=0) break;
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               passTime = System.currentTimeMillis() - start;
            }
        }
        return response;
    }


}