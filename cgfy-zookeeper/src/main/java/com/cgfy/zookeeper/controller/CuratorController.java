package com.cgfy.zookeeper.controller;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Curator主要解决了三类问题
 * 1.封装ZooKeeper client与ZooKeeper server之间的连接处理
 * 2.提供了一套Fluent风格的操作API
 * 3.提供ZooKeeper各种应用场景(recipe, 比如共享锁服务, 集群领导选举机制)的抽象封装
 */
@Api(tags = "Curator客户端", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@RequestMapping(value="/curator")
public class CuratorController {
    //Curator客户端
    public static CuratorFramework client = null;
    /**
     * 集群模式则是多个ip
     * private static final String zkServerIps = "192.168.10.124:2182,192.168.10.124:2183,192.168.10.124:2184";
     */
    @Value("${zookeeper.address}")
    private static String zkServerIps;

    /**
     * 创建会话
     * 1.使用静态工程方法创建
     * CuratorFramework client1 = CuratorFrameworkFactory.newClient(zkServerIps, 5000, 5000,  new ExponentialBackoffRetry(1000, 3));
     * 2.以下是使用Fluent风格api创建
     */
    public static CuratorFramework getConnection() {
        if (client == null) {
            //synchronized修饰类,给这个类加把锁,类的所有对象用的是同一把锁。
            synchronized (CuratorController.class) {
                if (client == null) {
                    //通过工程创建连接
                    client = CuratorFrameworkFactory.builder()
                            .connectString(zkServerIps)
                            .connectionTimeoutMs(5000) ///连接超时时间
                            .sessionTimeoutMs(5000)  // 会话超时时间
                            .retryPolicy(new ExponentialBackoffRetry(1000, 10))   // 重试策略：初试时间为1s 重试10次
//	           				.namespace("super")  // 设置命名空间进行隔离
                            .build();//开始建立连接

                    //开启连接
                    client.start();
                    //分布锁
                   // System.out.println(client.getState());
                }
            }
        }
        return client;
    }

    /**
     * 创建节点   不加withMode默认为持久类型节点
     * @param path  节点路径
     * @param value 值
     */
    @ApiOperation(value = "创建节点,不加withMode默认为持久类型节点", hidden=false)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public static String create(String path, String value) {
        try {
            //若创建节点的父节点不存在会先创建父节点再创建子节点
            return getConnection().create()
                    .creatingParentsIfNeeded()//递归创建所需父节点
                    //.withMode(CreateMode.PERSISTENT) // 创建类型为持久节点
                    .forPath("/super" + path, value.getBytes());//目录及内容
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建节点
     * @param path     节点路径
     * @param value    值
     * @param modeType 节点类型
     */
    @ApiOperation(value = "创建节点", hidden=false)
    @RequestMapping(value = "/createNode", method = RequestMethod.POST)
    public static String createNode(String path, String value, String modeType) {
        try {
            if (StringUtils.isEmpty(modeType)) {
                return null;
            }
            //持久型节点
            if (CreateMode.PERSISTENT.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create()
                        .creatingParentsIfNeeded()//递归创建所需父节点
                        .withMode(CreateMode.PERSISTENT)//创建类型为持久节点
                        .forPath("/super" + path, value.getBytes());//目录及内容
            }
            //临时节点
            if (CreateMode.EPHEMERAL.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/super" + path, value.getBytes());
            }

            //持久类型顺序性节点
            if (CreateMode.PERSISTENT_SEQUENTIAL.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/super" + path, value.getBytes());
            }

            //临时类型顺序性节点
            if (CreateMode.EPHEMERAL_SEQUENTIAL.equals(modeType)) {
                //若创建节点的父节点不存在会先创建父节点再创建子节点
                return getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/super" + path, value.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取单个节点
     *
     * @param path
     * @return
     */
    @ApiOperation(value = "获取单个节点", hidden=false)
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public static String getData(String path) {
        try {
            String str = new String(getConnection().getData().forPath("/super" + path));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取子节点
     *
     * @param path
     * @return
     */
    @ApiOperation(value = "获取子节点", hidden=false)
    @RequestMapping(value = "/getChildren", method = RequestMethod.GET)
    public static List<String> getChildren(String path) {
        try {
            List<String> list = getConnection().getChildren().forPath("/super" + path);// 获取子节点的路径
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 修改节点值
     *
     * @param path
     * @param valu
     * @return
     */
    @ApiOperation(value = "修改节点值", hidden=false)
    @RequestMapping(value = "/setData", method = RequestMethod.PUT)
    public static String setData(String path, String valu) {
        try {
            getConnection().setData().forPath("/super" + path, valu.getBytes());
            String str = new String(getConnection().getData().forPath("/super" + path));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除节点
     *
     * @param path
     */
    @ApiOperation(value = "删除节点", hidden=false)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public static void delete(String path) {
        try {
            getConnection().delete()
                    .guaranteed()// 强制保证删除
                    .deletingChildrenIfNeeded()// 递归删除子节点
                    .forPath("/super" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检测节点是否存在
     *
     * @param path
     * @return
     */
    @ApiOperation(value = "检测节点是否存在", hidden=false)
    @RequestMapping(value = "/checkExists", method = RequestMethod.POST)
    public static boolean checkExists(String path) {
        try {
            Stat s = getConnection().checkExists()// 检查是否存在
                    .forPath("/super" + path);
            return s == null ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 分布式锁 对象
     *
     * @param path
     * @return
     */
    @ApiOperation(value = "分布式锁", hidden=false)
    @RequestMapping(value = "/getLock", method = RequestMethod.POST)
    public static InterProcessMutex getLock(String path) {
        InterProcessMutex lock = null;
        try {
            lock = new InterProcessMutex(getConnection(), "/super" + path);
            return lock;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
//		if(checkExists("/qxw")){
//			delete("/qxw");
//		}
//		System.out.println("创建节点："+create("/qxw/q1", "苏打水法萨芬撒"));
//		System.out.println("创建节点："+create("/qxw/q2", "苏打水法萨芬撒"));
//		System.out.println("创建节点："+create("/qxw/q3", "苏打水法萨芬撒"));
//
//
//
//		ExecutorService pool = Executors.newCachedThreadPool();
//		getConnection().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
//			public void processResult(CuratorFramework cf, CuratorEvent ce) throws Exception {
//				System.out.println("code:" + ce.getResultCode());
//				System.out.println("type:" + ce.getType());
//				System.out.println("线程为:" + Thread.currentThread().getName());
//			}
//		}, pool)
//		.forPath("/super/qxw/q4","q4内容".getBytes());
//
//		System.out.println("读取节点： "+getData("/qxw"));
//		System.out.println("读取字节点："+getChildren("/qxw").toString());

        test();

    }

    /***
     * 分布锁演示
     * 分布式编程时，比如最容易碰到的情况就是应用程序在线上多机部署，于是当多个应用同时访问某一资源时，就需要某种机制去协调它们。
     * 例如，现在一台应用正在rebuild缓存内容，要临时锁住某个区域暂时不让访问；又比如调度程序每次只想一个任务被一台应用执行等等。
     * 线程会去争夺锁,拿到锁的线程会占用1秒
     */
    private static int count = 0;

    public static void test() throws InterruptedException {
        final InterProcessMutex lock = getLock("/lock");
        final CountDownLatch c = new CountDownLatch(10);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        c.countDown();
                        Thread.sleep(1000);
                        //加锁
                        lock.acquire();
                        System.out.println(System.currentTimeMillis() + "___" + (++count));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
        pool.shutdown();
        c.await();
        System.out.println("CountDownLatch执行完");
    }
}
