package com.shuyan.learn.java.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author will
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        LockThread lockThread = new LockThread();
        Thread thread1 = new Thread(lockThread, "SyncThread1");
        Thread thread2 = new Thread(lockThread, "SyncThread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

class LockThread implements Runnable {
    public static User1 from = new User1();
    // 注意 Lock 对象的定义方式及位置，同一个lock对象之间的锁是互斥的，否则互不影响
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            transferMoney(from,5.0f);
        }
    }

    private void transferMoney(User1 from, float amount){
        lock.lock();
        try {
            from.setMoney(from.getMoney() + amount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class User1{
    public float getMoney() {
        System.out.println(Thread.currentThread().getName() + ": get " + money);
        return money;
    }

    public void setMoney(float money){
        System.out.println(Thread.currentThread().getName() + ": set " + money);
        this.money = money;
    }

    private float money = 10000.00f;
}
