package com.thread;

public class Two_Phase_Termination {
    public static void main(String[] args) throws InterruptedException {
        Termination t = new Termination();
        t.start();
        Thread.sleep(3000);
        t.stop();
    }
}

class Termination{
    private Thread monitor;

    public void start(){
        monitor = new Thread(new Runnable() {
            @Override
            public void run() {
                 while (true){
                     Thread thread = Thread.currentThread();
                     if (thread.isInterrupted()){
                         System.out.println("do something");
                         break;
                     }
                     try {
                         Thread.sleep(1000);
                         System.out.println("执行监控记录");
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                         thread.interrupt(); //打断sleep会清除打断标记，应当重新设置标记
                     }
                 }

            }
        });
        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
    }

}
