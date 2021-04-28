package com.cgfy.thread.synchronize;

/**
 * 当一个线程访问对象的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块。
 * Synchronized修饰一个方法很简单，就是在方法的前面加synchronized，public synchronized void method(){}; synchronized修饰方法和修饰一个代码块类似，
 * 只是作用范围不一样，修饰代码块是大括号括起来的范围，而修饰方法范围是整个函数。如将的run方法改成如下的方式，实现的效果一样。
 */
public class Mthreads implements Runnable {
    private int count;

    public Mthreads() {
        count = 0;
    }

    /**
     * countAdd是一个synchronized的，printCount是非synchronized的。从运行结果中可以看出一个线程访问一个对象的synchronized代码块时，
     * 别的线程可以访问该对象的非synchronized代码块而不受阻塞。
     */
    public void countAdd() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
    public void printCount() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " count:" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.equals("mt1")) {
            countAdd();//synchronized代码块
        } else if (threadName.equals("mt2")) {
            printCount();//非synchronized代码块
        }
    }


    public static void main(String[] args) {
        System.out.println("使用关键字synchronized");
        Mthreads mt = new Mthreads();
        Thread thread1 = new Thread(mt, "mt1");
        Thread thread2 = new Thread(mt, "mt2");
        thread1.start();
        thread2.start();
    }
}
