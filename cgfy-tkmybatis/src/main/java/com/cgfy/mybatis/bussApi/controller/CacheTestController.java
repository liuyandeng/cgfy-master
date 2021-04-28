package com.cgfy.mybatis.bussApi.controller;

import com.cgfy.mybatis.base.bean.AjaxResponse;
import com.cgfy.mybatis.base.controller.BaseController;
import com.cgfy.mybatis.bussApi.domain.model.TestGen;
import com.cgfy.mybatis.bussApi.service.impl.CacheTestServiceImpl;
import com.cgfy.mybatis.base.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 「cgfy」基础Controller
 *
 * @author generator
 */
@Api(value = "缓存测试", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("CacheTestController")
@RequestMapping("/CacheTest")
public class CacheTestController extends BaseController {

	/**
	* 「cgfy」Service
	*/
    @Resource
    private CacheTestServiceImpl cacheService;

    /**
     * 缓存指定数据
     * @param id 主键id
     * @return 输出对象
     */
    @ApiOperation(value = "缓存指定数据", hidden=false)
    @RequestMapping(value = "/selectById/{id}", method = RequestMethod.GET)
    public AjaxResponse<TestGen> selectById(@PathVariable String id) {
        return AjaxResponse.success(cacheService.selectById(id));
    }


    /**
    * 缓存所有数据
    */
    @ApiOperation(value = "缓存所有数据", hidden=false)
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public AjaxResponse<List<TestGen>> selectAll() {
       /***************************redis 操作测试*****************************/
        RedisUtil.set("StringTest","刘彦登测试redis",30);
        System.out.println(RedisUtil.get("StringTest")+"过期时间:"+RedisUtil.getExpire("StringTest"));
        Map map=new HashMap();
        map.put("test001","test001");
        map.put("test002","test002");
        Map map2=new HashMap();
        map2.put("test003","test003");
        map2.put("test004","test004");
        List list=new ArrayList<>();
        list.add(map);
        list.add(map2);
        RedisUtil.hset("MapTest","MapItem",list,30);
        RedisUtil.lSet("ListTest",list,30);
        return AjaxResponse.success(cacheService.selectAll());
    }


    /**
     * 满足条件缓存数据
     * @param id 主键id
     * @return 输出对象
     */
    @ApiOperation(value = "满足条件缓存数据", hidden=false)
    @RequestMapping(value = "/selectByIdWithCondition/{id}/{number}", method = RequestMethod.GET)
    public AjaxResponse<TestGen> selectByIdWithCondition(@PathVariable String id, @PathVariable int number) {
        return AjaxResponse.success(cacheService.selectByIdWithCondition(id,number));
    }


    /**
     * 满足条件时否决缓存数据
     * @param id 主键id
     * @return 输出对象
     */
    @ApiOperation(value = "满足条件时否决缓存数据", hidden=false)
    @RequestMapping(value = "/selectByIdWithUnless/{id}/{number}", method = RequestMethod.GET)
    public AjaxResponse<TestGen> selectByIdWithUnless(@PathVariable String id, @PathVariable int number) {
        return AjaxResponse.success(cacheService.selectByIdWithUnless(id,number));
    }
    /**
    * 新增
    * @param input 输入参数
    * @return 输出对象
    */
    @ApiOperation(value = "新增", hidden=false)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public AjaxResponse<TestGen> insert(@RequestBody TestGen input) {
        return AjaxResponse.success(cacheService.insert(input));
    }

    /**
     * 有条件新增
     * @param input 输入参数
     * @return 输出对象
     */
    @ApiOperation(value = "有条件新增", hidden=false)
    @RequestMapping(value = "/insertWithCondition", method = RequestMethod.POST)
    public AjaxResponse<TestGen> insertWithCondition(@RequestBody TestGen input) {
        return AjaxResponse.success(cacheService.insertWithCondition(input));
    }

    /**
     * 更新
     * @param input 输入参数
     * @return 输出对象
     */
    @ApiOperation(value = "更新", hidden=false)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AjaxResponse<TestGen> update(@RequestBody TestGen input) {
        return AjaxResponse.success(cacheService.update(input));
    }



    /**
    * 根据key删除缓存区中的数据
    * @param id 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "根据key删除缓存区中的数据", hidden=false)
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public AjaxResponse<Object> delete(@PathVariable String id) {
        return AjaxResponse.success(cacheService.delete(id));
    }



    /**
     * 根据key删除缓存区中的数据
     * @param id 主键id
     * @return 输出对象
     */
    @ApiOperation(value = "删除所有缓存", hidden=false)
    @RequestMapping(value="/deleteByIdAndCleanCache/{id}", method = RequestMethod.DELETE)
    public AjaxResponse<Object> deleteByIdAndCleanCache(@PathVariable String id) {
        return AjaxResponse.success(cacheService.deleteByIdAndCleanCache(id));
    }


	

}

