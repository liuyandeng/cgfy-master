package com.cgfy.redisson.redisson.service.impl;
import com.cgfy.redisson.Redisson.service.CollectionsService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CollectionsServiceImpl implements CollectionsService {


    public RList rlist(RedissonClient client) {
        // RList 继承了 java.util.List 接口
        RList<String> rlist = client.getList("rlist");
        rlist.clear();
        rlist.add("test001");
        rlist.add("test002");
        rlist.add("http://cgfytop.cn");
        rlist.remove(-1);
        boolean contains = rlist.contains("test001");
        System.out.println("List size: " + rlist.size());
        System.out.println("Is list contains 'test001': " + contains);
        rlist.forEach(System.out::println);
       // client.shutdown();
        return rlist;
    }
    public RMap rmap(RedissonClient client) {
        // RMap 继承了 java.util.concurrent.ConcurrentMap 接口
        RMap<String, String> rmap = client.getMap("rmap");
        rmap.put("name", "晨港飞燕");
        rmap.put("city", "天津");
        rmap.put("link", "http://www.cgfytop.cn");
        boolean contains = rmap.containsKey("link");
        System.out.println("Map size: " + rmap.size());
        System.out.println("Is map contains key 'link': " + contains);
        String value = rmap.get("name");
        System.out.println("Value mapped by key 'name': " + value);
        boolean added = rmap.putIfAbsent("link", "https://www.baidu.com") == null;
        System.out.println("Is value mapped by key 'link' added: " + added);
        return rmap;
    }







}
