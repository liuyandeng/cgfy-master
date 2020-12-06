# 锁

## 介绍
- [redisson](https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95)
- java
- sql


## synchronized

 https://www.cnblogs.com/weibanggang/p/9470718.html
 
 https://blog.csdn.net/zjy15203167987/article/details/82531772
 
 https://www.jianshu.com/p/d53bf830fa09
 
1.为什么要使用synchronized

在并发编程中存在线程安全问题，主要原因有：
- 1.存在共享数据 
- 2.多线程共同操作共享数据。

关键字synchronized可以保证在同一时刻，只有一个线程可以执行某个方法或某个代码块，同时synchronized可以保证一个线程的变化可见（可见性），即可以代替volatile。
