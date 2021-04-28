package com.cgfy.thread.synchronize;

/**
 * 一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞。
 */
public class SyncThread implements Runnable{
    private static int count;
    public SyncThread() {
        count = 0;
    }

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("线程名:" + Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static void main(String[] args) {
//        System.out.println("使用关键字synchronized");
//        SyncThread syncThread = new SyncThread();
//        Thread thread1 = new Thread(syncThread, "SyncThread1");
//        Thread thread2 = new Thread(syncThread, "SyncThread2");
//        thread1.start();
//        thread2.start();
//    }

    /**
     * 创建了两个SyncThread的对象syncThread1和syncThread2，线程thread1执行的是syncThread1对象中的synchronized代码(run)，
     * 而线程thread2执行的是syncThread2对象中的synchronized代码(run)；我们知道synchronized锁定的是对象，
     * 这时会有两把锁分别锁定syncThread1对象和syncThread2对象，而这两把锁是互不干扰的，不形成互斥，所以两个线程可以同时执行。
     * 此处思考一个问题：为什么分布式环境下synchronized失效？如何解决这种情况？
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("使用关键字synchronized每次调用进行new SyncThread()");
        Thread thread1 = new Thread( new SyncThread(), "SyncThread1");
        Thread thread2 = new Thread( new SyncThread(), "SyncThread2");
        thread1.start();
        thread2.start();
    }


}
