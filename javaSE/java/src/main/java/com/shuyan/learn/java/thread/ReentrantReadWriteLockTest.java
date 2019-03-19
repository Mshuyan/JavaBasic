package com.shuyan.learn.java.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试读写锁 {@link ReentrantReadWriteLock}
 * @author will
 */
public class ReentrantReadWriteLockTest {


}

class ReadThread implements Runnable{
    // 这里必须使用 ReadWriteLock 接收，不能再使用 Lock
    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void run() {
        for(int i=0; i<5; i++){
            read();
        }
    }

    public void read(){
        lock.readLock().lock();
        try{
            // 执行读
        }finally {
            lock.readLock().unlock();
        }
    }
}
