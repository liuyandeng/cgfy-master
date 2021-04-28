package com.cgfy.thread.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class TestIcon {
    public static void main(String[] args){
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int x = 0;x < 10; x++){
            new Thread(atomicDemo).start();
        }
    }
}

//内部类
class AtomicDemo implements Runnable{
    AtomicInteger i = new AtomicInteger();//线程安全的
    public int getI(){
        return i.getAndIncrement();
    }
   // private int i = 0;//线程不安全的
//    public int getI(){
//        return i++;
//    }
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getI());//计数器+1
    }
}