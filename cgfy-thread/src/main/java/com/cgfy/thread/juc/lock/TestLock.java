package com.cgfy.thread.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        Ticket td = new Ticket();
        new Thread(td, "窗口1").start();
        new Thread(td, "窗口2").start();
        new Thread(td, "窗口3").start();
    }
}

//class Ticket implements Runnable {
//    private int ticket = 100;
//    @Override
//    public void run() {
//        while (true) {
//            if (ticket > 0) {
//                try {
//                    Thread.sleep(200);
//                } catch (Exception e) {
//                }
//                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + (--ticket));
//            }
//        }
//    }
//}

class Ticket implements Runnable {
    private Lock lock = new ReentrantLock();//创建lock锁
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            lock.lock();//上锁
            try {
                if (ticket > 0) {
                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                    }
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + (--ticket));
                }
            }finally {
                lock.unlock();//释放锁
            }

        }
    }
}