package com.cgfy.mybatis.bussApi.service.impl;

import com.cgfy.mybatis.bussApi.bean.TestGenInputBean;
import com.cgfy.mybatis.bussApi.bean.TestGenOutputBean;
import com.cgfy.mybatis.bussApi.domain.mapper.TestGenMapper;
import com.cgfy.mybatis.bussApi.domain.model.TestGen;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 指定默认缓存区
 * 缓存区：key的前缀，与指定的key构成redis的key，如 user::list
 */
@CacheConfig(cacheNames = "user")
@Service
public class CacheTestServiceImpl {
    @Resource
    TestGenMapper mapper;

    /**
     * @Cacheable 缓存有数据时，从缓存获取；没有数据时，执行方法，并将返回值保存到缓存中
     * @Cacheable 一般在查询中使用
     * 1) cacheNames 指定缓存区，没有配置使用@CacheConfig指定的缓存区
     * 2) key 指定缓存区的key
     * 3) 注解的值使用SpEL表达式
     * eq ==
     * lt <
     * le <=
     * gt >
     * ge >=
     * 4) condition 执行方法前判断是否使用注解的功能
     * 5) unless 执行方法后,判断是否使用注解提供的功能
     * 5)cacheManager
     */
    @Cacheable(cacheNames = "user", key = "#id")
    public TestGen selectById(String id) {
        return mapper.selectByPrimaryKey(id);
    }


    @Cacheable(key = "'list'")
    public List<TestGen> selectAll() {
        return mapper.selectAll();
    }

    /**
     * condition 满足条件缓存数据
     */
    @Cacheable(key = "#id", condition = "#number ge 20") // >= 20
    public TestGen selectByIdWithCondition(String id, int number) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * unless 满足条件时否决缓存数据
     */
    @Cacheable(key = "#id", unless = "#number lt 20") // < 20
    public TestGen selectByIdWithUnless(String id, int number) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * @CachePut 一定会执行方法，并将返回值保存到缓存中
     * @CachePut 一般在新增和修改中使用
     */
    @CachePut(key = "#user.id")
    public TestGen insert(TestGen user) {
        mapper.insert(user);
        return user;
    }


    @CachePut(key = "#user.id", condition = "#user.age ge 20")
    public TestGen insertWithCondition(TestGen user) {
        mapper.insert(user);
        return user;
    }


    @CachePut(key = "#user.id")
    public TestGen update(TestGen user) {
        mapper.updateByPrimaryKey(user);
        return user;
    }
    /**
     * 根据key删除缓存区中的数据
     */
    @CacheEvict(key = "#id")
    public int delete(String id) {
        return mapper.deleteByPrimaryKey(id);
    }


    /**
     * allEntries:值为true则不管key,清除cacheNames下所有的缓存
     * beforeInvocation:默认false,表示调用方法之后删除缓存数据；true时，在调用方法之前删除缓存数据(如方法出现异常)
     */
    @CacheEvict(key = "#id", allEntries = true)
    public int deleteByIdAndCleanCache(String id) {
        return mapper.deleteByPrimaryKey(id);
    }




    public List<TestGen> selectPage(TestGenInputBean input, int page, int rows) {
        Example example = new Example(TestGen.class);
        Example.Criteria criteria = example.createCriteria();
        if (input.getName() != null) {
            criteria.andLike("name", input.getName() + "%");//名字含有某个关键字的记录
        }
        if (input.getAge() != null) {
            criteria.andGreaterThan("age", input.getAge());//大于某个年龄段
        }
        example.orderBy("age").desc();//按照年龄排序
        //分页查询
        PageHelper.startPage(page, rows);
        List<TestGen> result = mapper.selectByExample(example);
        return result;
    }


    public List<TestGenOutputBean> selectPageBySql(TestGenInputBean input, int page, int rows) {
        PageHelper.startPage(page, rows);
        Page<TestGen> page1 = (Page<TestGen>) mapper.selectAll();
        Page<TestGen> pages = PageHelper.startPage(page, rows);
        List<TestGenOutputBean> result = null;
        //result=mapper.selectPageBySql(input);
        return result;
    }


    public Page<TestGenOutputBean> selectPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TestGen> page = (Page<TestGen>) mapper.selectAll();
        return transformVO(page, page.getResult().stream().map(e -> {
            TestGenOutputBean vo = new TestGenOutputBean();
            vo.setId(e.getId());
            vo.setName(e.getName());
            vo.setSex(e.getSex());
            return vo;
        }).collect(Collectors.toList()));
    }

    public static <T, E> Page<T> transformVO(Page<E> poPage, List<T> voList) {
        Page<T> page = new Page<>();
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(poPage, page);
        } catch (Exception e) {
        }
        page.addAll(voList == null ? Lists.newArrayList() : voList);
        return page;
    }
}

