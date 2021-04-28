package com.cgfy.thread.synchronize;

/**
 * 修饰静态方法
 * 我们知道静态方法是属于类的而不属于对象的。同样的，synchronized修饰的静态方法锁定的是这个类的所有对象。
 * syncThread1和syncThread2是StaticMthreads的两个对象，但在thread1和thread2并发执行时却保持了线程同步。
 * 这是因为run中调用了静态方法method，而静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁。这与使用关键字synchronized运行结果相同
 */
public class StaticMthreads  implements Runnable  {
    private static int count;

    public StaticMthreads() {
        count = 0;
    }

    public synchronized static void method() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void run() {
        method();
    }

    public static void main(String[] args) {
        System.out.println("使用关键字静态synchronized");
        StaticMthreads thread = new StaticMthreads();
        Thread thread1 = new Thread(thread, "SyncThread1");
        Thread thread2 = new Thread(thread, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
