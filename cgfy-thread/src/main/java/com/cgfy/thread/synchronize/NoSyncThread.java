package com.cgfy.thread.synchronize;

/**
 * 当两个并发线程(thread1和thread2)访问同一个对象(syncThread)中的synchronized代码块时，在同一时刻只能有一个线程得到执行，
 * 另一个线程受阻塞，必须等待当前线程执行完这个代码块以后才能执行该代码块。Thread1和thread2是互斥的，因为在执行synchronized代码块时会锁定当前的对象，
 * 只有执行完该代码块才能释放该对象锁，下一个线程才能执行并锁定该对象。
 */
public class NoSyncThread implements Runnable {
    private static int count;

    public NoSyncThread() {
        count = 0;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("线程名:" + Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("不使用关键字synchronized");
        NoSyncThread syncThread = new NoSyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
