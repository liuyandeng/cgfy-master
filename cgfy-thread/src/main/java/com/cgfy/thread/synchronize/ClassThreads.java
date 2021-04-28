package com.cgfy.thread.synchronize;

/**
 * 修饰一个类
 * 运行效果和synchronized修饰静态方法是一样的，synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁。
 */
//class ClassName {
//    public void method() {
//        synchronized(ClassName.class) {
//
//        }
//    }
//}


public class ClassThreads  implements Runnable{
    private static int count;

    public ClassThreads() {
        count = 0;
    }

    public static void method() {
        synchronized(SyncThread.class) {
            for (int i = 0; i < 5; i ++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void run() {
        method();
    }

    public static void main(String[] args) {
        System.out.println("使用ClassName");
        ClassThreads thread = new ClassThreads();
        Thread thread1 = new Thread(thread, "SyncThread1");
        Thread thread2 = new Thread(thread, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
