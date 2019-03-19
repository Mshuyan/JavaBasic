package com.shuyan.learn.java.thread;

/**
 * 演示锁的可重入特性
 * @author will
 */
public class ReentrantLockTest {
    synchronized void method1(){
        method2();          // 调用 method2 时，因为 this对象已经被当前线程锁住，
                            // 所以不会再次获取该锁，直接执行method2
    }
    synchronized void method2(){
    }
}
