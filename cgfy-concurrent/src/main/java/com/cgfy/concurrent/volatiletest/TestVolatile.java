package com.cgfy.concurrent.volatiletest;

import lombok.Data;

public class TestVolatile {
    public static void main(String[] args){ //这个线程是用来读取flag的值的
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();//这个线程是用来修改flag的值为true
        while (true){
            if (threadDemo.isFlag()){//flag为true进入判断逻辑,输出语句
                System.out.println("主线程读取到的flag = " + threadDemo.isFlag());//flag为true时输出这段话
                break;
            }
        }
    }
}


//内部类
@Data
class ThreadDemo implements Runnable{ //这个线程是用来修改flag的值的
    // public volatile boolean flag = false;
    public  boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("ThreadDemo线程修改后的flag = " + isFlag());
    }
}