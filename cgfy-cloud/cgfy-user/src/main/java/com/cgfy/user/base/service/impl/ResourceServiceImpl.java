package com.cgfy.user.base.service.impl;

import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.base.bean.SelectOutputBean;
import com.cgfy.user.bussApi.feign.bean.CodeOutputBean;
import com.cgfy.user.base.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类是资源(码表等)加载service实现类
 */

@Service("resService")
public class ResourceServiceImpl implements ResourceService {
    private static Map<String, Map<String, Object>> validCodeMap = new HashMap<String, Map<String, Object>>();
    private static Map<String, Map<String, Object>> invalidCodeMap = new HashMap<String, Map<String, Object>>();
    int codeNum = 0;
    int typeNum = 0;

    /**
     * 加载码表到缓存中
     */
    public void loadCodeTable() {
        //Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();
        try {
            System.out.println("resourceService - 开始获取码表！ ");
            fetchCode();
            System.out.println("resourceService - 码表放入缓存中时，成功！ ");
            /**
             *  20200902注释 CacheManager在SpringContextAware中传入ApplicationContext创建实例(单例)
             *  CacheManager通过getBean获取cacheOperator
             *  doPutToServletCache调用cacheOperator的doPutToServletCache方法,RedisCacheOperator实现cacheOperator
             *  Globals.CODE_TABLE_ATTR="_SYSTEM_CODE_TABLE_CONTENT_KEY_"
             */
            //CacheManager.getInstance().doPutToServletCache(Globals.CODE_TABLE_ATTR, map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("resourceService - 码表放入缓存中时，失败！ ");
            e.printStackTrace();
        }
			System.out.println("resourceService - 码表数据缓存成功,共计"+ codeNum + "条记录 ");
    }

    public List<Map<String, Object>> getKeyValueByCode(String codeType) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        boolean flag = validCodeMap.containsKey(codeType);
        if (flag) {
            Map<String, Object> map = validCodeMap.get(codeType);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Map<String, Object> mapx = new HashMap<String, Object>();
                mapx.put("value", entry.getKey());
                mapx.put("label", entry.getValue());
                list.add(mapx);
            }
        }
        return list;
    }

    public String getValueByCodeAndKey(String codeType, String codeValue) {
        String codeName = "";
        boolean flag = validCodeMap.containsKey(codeType);
        if (flag) {
            Map<String, Object> map = validCodeMap.get(codeType);
            boolean flag1 = map.containsKey(codeValue);
            if (flag1) return map.get(codeValue).toString();
        }
        boolean flag2 = invalidCodeMap.containsKey(codeType);
        if (flag2) {
            Map<String, Object> map2 = invalidCodeMap.get(codeType);
            boolean flag3 = map2.containsKey(codeValue);
            if (flag3) return map2.get(codeValue).toString();
        }
        return codeName;
    }

    private void fetchCode() {
        AjaxResponse<SelectOutputBean<CodeOutputBean>> feignResult = null;
       /// feignResult = userFeignClient.selectEx();//从其他服务中feign调用数据,也可以从本服务获取用户相关数据
        if (feignResult == null || !feignResult.isSuccess())
            return;
        SelectOutputBean<CodeOutputBean> sob = feignResult.getData();
        codeNum= sob.getRecords().size();
        for (CodeOutputBean code : sob.getRecords()) {
            if (code.getStatus().equals("0")) {
                boolean flag = validCodeMap.containsKey(code.getCodeType());
                if (flag) {
                    Map<String, Object> map = validCodeMap.get(code.getCodeType());
                    map.put(code.getCodeValue(), code.getCodeName());
                } else {
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put(code.getCodeValue(), code.getCodeName());
                    validCodeMap.put(code.getCodeType(), map1);
                }
            } else {
                boolean flag2 = invalidCodeMap.containsKey(code.getCodeType());
                if (flag2) {
                    Map<String, Object> map2 = invalidCodeMap.get(code.getCodeType());
                    map2.put(code.getCodeValue(), code.getCodeName());
                } else {
                    Map<String, Object> map3 = new HashMap<String, Object>();
                    map3.put(code.getCodeValue(), code.getCodeName());
                    invalidCodeMap.put(code.getCodeType(), map3);
                }
            }
        }
        //因为是联合主键的设计,下面这种写法是错误的,因为一种类型可以有禁用和未禁用两个状态,比如info_type
        //typeNum=validCodeMap.size()+invalidCodeMap.size();
    }


}
