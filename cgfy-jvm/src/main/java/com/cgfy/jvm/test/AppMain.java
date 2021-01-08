package com.cgfy.jvm.test;

public class AppMain {
    //运行时, jvm 把appmain的信息都放入方法区
    public static void main(String[] args) {
        //main 方法本身放入方法区。
        Sample test1 = new Sample(" 测试1 ");
        //test1是引用，所以放到栈区里， Sample是自定义对象应该放到堆里面
        Sample test2 = new Sample(" 测试2 ");

        test1.printName();
        test2.printName();
    }
}

class Sample {
    //运行时, jvm 把appmain的信息都放入方法区
    private String name;
    //new Sample实例后， name 引用放入栈区里，  name 对象放入堆里

    public Sample(String name) {
        this.name = name;
    }

    //print方法本身放入 方法区里。
    public void printName() {
        System.out.println(name);
    }
}
