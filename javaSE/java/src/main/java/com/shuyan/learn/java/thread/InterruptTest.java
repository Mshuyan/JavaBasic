package com.shuyan.learn.java.thread;

import static java.lang.Thread.sleep;

/**
 * @author will
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new TestThread(),"thread");
        thread.start();
        sleep(1000);
        thread.interrupt();
        thread.join();
    }
}

class TestThread implements Runnable{
    private static long num = 0;
    @Override
    public void run() {
        while (true){
            if(num > 0){
                System.out.println("running");
            }
            if(Thread.currentThread().isInterrupted()){
                System.out.println("interrupted");
                num ++;
            }
        }
    }
}
