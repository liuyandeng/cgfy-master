package com.cgfy.thread.juc.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriterLock {
    public static void main(String[] args){
        ReadWriterLockDemo rw = new ReadWriterLockDemo();
        new Thread(new Runnable() {//一个线程写
            @Override
            public void run() {
                rw.set((int)Math.random()*101);
            }
        },"write:").start();
        for (int i = 0;i<100;i++){//100个线程读
            Runnable runnable = () -> rw.get();
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}

class ReadWriterLockDemo{
    private int number = 0;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读(可以多个线程同时操作)
    public void get(){
        readWriteLock.readLock().lock();//上锁
        try {
            System.out.println(Thread.currentThread().getName()+":"+number);
        }finally {
            readWriteLock.readLock().unlock();//释放锁
        }
    }
    //写(一次只能有一个线程操作)
    public void set(int number){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }
}