package com.shuyan.learn.java.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;

public class RealizationThread {
    public static void main(String[] args) {
        // 继承Thread，重写run
        Thread1 thread1 = new Thread1();
        thread1.start();
        // 内部类重写run方法
        new Thread(){
            @Override
            public void run() {
                System.out.println("thread2");
            }
        }.start();
        // 实现Runable接口
        Thread thread3 = new Thread(new Thread3());
        thread3.start();
        // 内部类实现Runable接口
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread4");
            }
        }).start();
        // Callable方式
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("thread5");
                return 1;
            }
        });
        Thread thread5 = new Thread(task);
        thread5.start();
        // 判断线程是否执行结束，非阻塞
         System.out.println(task.isDone());
        try {
            // 等待线程执行结束并获取返回值，阻塞
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(task.isDone());
        // Thread6
        Thread6 thread6 = new Thread6();
        Thread thread61 = new Thread(thread6);
        Thread thread62 = new Thread(thread6);
        thread61.start();
        thread62.start();
    }
}

class Thread7 implements Runnable{
    AtomicInteger integer = new AtomicInteger();
    @Override
    public void run() {
        System.out.println("thread7");
    }
}

class Thread6 implements Runnable{
    // 将1个Integer类型的局部变量与线程绑定，初始值为0
    static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);
    @Override
    public void run() {
        local.set(local.get() + 1);
        System.out.println("thread6 local = " + local.get());
    }
}

class Thread3 implements Runnable{
    @Override
    public void run() {
        System.out.println("thread3");
    }
}

class Thread1 extends Thread{
    @Override
    public void run() {
        System.out.println("thread1");
    }
}


