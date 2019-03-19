package com.shuyan.learn.java.thread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Test1 test1 = new Test1();
        Thread thread1 = new Thread(test1);
        Thread thread2 = new Thread(test1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }
}

class Test1 implements Runnable {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Override
    public void run() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                condition.signal();
                condition.await();
            }
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}