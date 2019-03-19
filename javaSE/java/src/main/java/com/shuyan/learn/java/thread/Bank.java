package com.shuyan.learn.java.thread;

/**
 * @author will
 */
public class Bank {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Banker(), "SyncThread1");
        Thread thread2 = new Thread(new Banker(), "SyncThread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

class Banker implements Runnable{
    public static User from = new User();
    public static User to = new User();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                transferMoney(from,to,5.0f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void transferMoney(User from, User to, float amount){
        synchronized (to) {
            to.setMoney(to.getMoney() + amount);
        }
        synchronized (from) {
            from.setMoney(from.getMoney() - amount);
        }
    }
}

class User{
    public float getMoney() {
        String user = (this == Banker.from)? "from" : "to";
        System.out.println(Thread.currentThread().getName() + ": get " + user + " = " + money);
        return money;
    }

    public void setMoney(float money) {
        String user = (this == Banker.from)? "from" : "to";
        System.out.println(Thread.currentThread().getName() + ": set " + user + " = " + money);
        this.money = money;
    }

    private float money = 10000.00f;


}
