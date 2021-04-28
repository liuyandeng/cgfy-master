package com.cgfy.thread.juc.copyonwritearraylist;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestConcurrent {
    public static void main(String[] args){
        ThreadDemo2 threadDemo2 = new ThreadDemo2();
        for (int i=0;i<10;i++){
            new Thread(threadDemo2).start();
        }
    }
}
//10个线程同时访问
class ThreadDemo2 implements Runnable{
   // private static List<String> list = Collections.synchronizedList(new ArrayList<>());//普通做法
   private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }
    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());//读
            list.add("ddd");//写
        }
    }
}