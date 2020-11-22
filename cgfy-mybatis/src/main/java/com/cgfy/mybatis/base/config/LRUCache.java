package com.cgfy.mybatis.base.config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Java 版的 LRU
 * LRU是Least Recently Used的缩写，即最近最少使用，是一种常用的页面置换算法，选择最近最久未使用的页面予以淘汰。
 * 该算法赋予每个页面一个访问字段，用来记录一个页面自上次被访问以来所经历的时间 t，当须淘汰一个页面时，选择现有页面中其 t 值最大的，
 * 即最近最少使用的页面予以淘汰。---来自百度百科 LRU
 * 配置文件maxmemory参数设置Redis最大占用内存,64位系统不限制内存，32位系统最多使用3GB内存。如果设置了maxmemory，一般都要设置过期策略。
 * 打开Redis的配置文件有如下描述，Redis有六种过期策略：
 * ①noeviction:  当内存不足以容纳新写入数据时，新写入操作会报错，这个一般没人用吧，实在是太恶心了。
 * ②allkeys-lru：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的 key（这个是最常用的）。
 * ③allkeys-random：当内存不足以容纳新写入数据时，在键空间中，随机移除某个 key，这个一般没人用吧，为啥要随机，肯定是把最近最少使用的 key 给干掉啊。
 * ④volatile-lru：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，移除最近  最少使用的 key（这个一般不太合适）。
 * ⑤volatile-random：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，随机  移除某个 key。
 * ⑥volatile-ttl：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，有更早过  期时间的 key 优先移除。
 * 那么打开配置文件，添加如下一行，使用allkeys-lru的过期策略
 * maxmemory-policy allkeys-lru
 * 保存文件退出，重启redis服务
 * 其他lru工具:https://blog.csdn.net/fenglllle/article/details/82659576
 * @param <K>
 * @param <V>
 */
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;
    /**
     * 传递进来最多能缓存多少数据
     *
     * @param cacheSize 缓存大小
     */
    public LRUCache(int cacheSize) {
        // true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    /**
     * 钩子方法，通过put新增键值对的时候，若该方法返回true
     * 便移除该map中最老的键和值
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
        return size() > CACHE_SIZE;
    }

}
