package com.shuyan.learn.java.thread;

import lombok.Synchronized;

/**
 * @author will
 */

public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        test1();
        System.out.println("\r\ntest1执行结束\r\n");
        test2();
    }

    private static void test1() throws InterruptedException {
        // 锁住同一个对象时，只有1个线程锁住的内容执行结束才能执行另外一个线程
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static void test2() throws InterruptedException {
        // 锁住不同对象时，两个线程互不影响
        Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
        Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

class SyncThread implements Runnable {
    private int count;

    public SyncThread() {
        count = 0;
    }

    /**
     * @Synchronized 等价于 synchronized(this){}
     */
    @Override
    @Synchronized
    public  void run() {
        // this 对象被锁住时，不能执行{}内的代码
        //synchronized(this) {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //}
    }
}
