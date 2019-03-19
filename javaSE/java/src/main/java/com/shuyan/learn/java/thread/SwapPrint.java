package com.shuyan.learn.java.thread;

public class SwapPrint {
    static volatile Integer num = 0;
    static volatile Boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (num < 10000){
                if(!flag ){
                    num ++;
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    flag = true;
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (num < 10000){
                if(flag ){
                    num ++;
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    flag = false;
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
