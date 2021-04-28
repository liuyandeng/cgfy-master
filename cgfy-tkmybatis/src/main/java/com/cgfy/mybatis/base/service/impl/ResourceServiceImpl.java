package com.cgfy.mybatis.base.service.impl;


import com.cgfy.mybatis.base.bean.AjaxResponse;
import com.cgfy.mybatis.base.bean.SelectOutputBean;
import com.cgfy.mybatis.base.service.ResourceService;
import com.cgfy.mybatis.generator.bean.CodeInfoBean;
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
   // @Autowired
  //  private UserFeignClient userFeignClient;
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
            //RedisTemplate已在RedisConfig序列化
            //CacheManager.getInstance().doPutToServletCache(Globals.CODE_TABLE_ATTR, map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("resourceService - 码表放入缓存中时，失败！ ");
            e.printStackTrace();
        }
			System.out.println("resourceService - 码表数据缓存成功,共计"+ codeNum + "条记录 ");
    }


    private void fetchCode() {
        AjaxResponse<SelectOutputBean<CodeInfoBean>> feignResult = null;
        //feignResult = userFeignClient.selectEx(); //远程调用
        if (feignResult == null || !feignResult.isSuccess())
            return;
        SelectOutputBean<CodeInfoBean> sob = feignResult.getData();
        codeNum= sob.getRecords().size();
        for (CodeInfoBean code : sob.getRecords()) {
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











    /**
     * uums写法(UserFeignClient)
     * @Autowired
     * private JBaseDao jBaseDao;
     * @Autowired
     * private UumsProperties uumsProperties;   //（@Component @ConfigurationProperties(prefix="cgfy.uums") public class UumsProperties）
     * private List<String> codeSqls;//码表加载sql list

     * yml文件
     * # 自定义属性
     * # 平台使用 JbaseDao 进行数据库查询时，最大查询记录数，为0时，不限制
     * cgfy:
     *   jdbc:
     *     maxRowsQuery: 10000
     *   # 系统管理员ID，多个“,”分割
     *   uums:
     *     sys_admins: admin,ADMINID1000,526,ZhongFaZhan
     *     # 码表sql
     *     codeSql:
     *       # 码表
     *       - select CODE_TYPE as ctype, CODE_VALUE as ckey, CODE_NAME as cname, STATUS as cstatus from sys_code_info order by ORDER_NO
     *       # 子系统
     *       - select 'SUBSYS' as ctype, ID AS ckey, CONCAT(NAME,'(',CODE,')') as cname, '0' as cstatus FROM sys_subsystem_info
     *       # 用户
     *       - select 'SYS_USER' as ctype, a.id as ckey, a.name as cname, a.STATUS as cstatus from sys_user_info as a order by ORG_ID, ORDER_NO
     *       # 机构
     *       - select 'SYS_ORG' as ctype, a.id as ckey, a.name as cname, a.STATUS as cstatus from sys_org_info as a order by PARENT_ID,ORDER_NO
     *       # 岗位
     *       - select 'SYS_GROUP' as ctype, a.id as ckey, a.name as cname, a.STATUS as cstatus from sys_group_info as a order by ORG_ID
     */
    /**
     * 加载码表到缓存中
     */
/*    public void loadCodeTable(){
        System.out.println("resourceService - 开始读取码表数据信息...... ");
        codeSqls = uumsProperties.getCodeSql();

        int codeNum = 0;
        int typeNum = 0;
        if (codeSqls == null || codeSqls.size() == 0){
            System.out.println("resourceService - xml文件中未找到码表配置. ");
        }else{
            *//** 定义缓存码表的Map **//*
            Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String, Object>>>();

            *//** 循环配置的码表sql **//*
            for (int i = 0; i < codeSqls.size(); i++){
                *//** 根据sql 获取数据库数据 **//*
                String sql = codeSqls.get(i);
                List<Map<String, Object>> ctList = this.jBaseDao.queryForList(sql);
                codeNum += ctList.size();

                for (int j = 0; j < ctList.size(); j++){
                    Map<String, Object> tpMap = ctList.get(j);
                    String type = Objects.toString(tpMap.get("ctype"), "");

                    List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                    if (map.containsKey(type)){
                        *//** 如果类型已经加入到Map中，就继续向list中增加内容 *//*
                        mapList = (List<Map<String, Object>>) map.get(type);
                    }

                    mapList.add(tpMap);
                    map.put(type, mapList);
                }
            }

            typeNum = map.size();

            try {
                CacheManager.getInstance().doPutToServletCache(Globals.CODE_TABLE_ATTR, map);
            } catch (Exception e) {
                System.out.println("resourceService - 码表放入缓存中时，失败！ ");
                e.printStackTrace();
            }

            System.out.println("resourceService - 码表数据缓存成功,共计" + typeNum + "类" + codeNum + "条记录 ");
        }
    }*/


}
