package com.cgfy.user.bussApi.feign;


import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.bussApi.feign.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFeignService {

    @Autowired
    private UserFeignClient userFeignClient;


    //通过门户人事系统添加人员
    public UserInfoOutputBean insertFromHr(UserInfoInputBean input){
        AjaxResponse<UserInfoOutputBean>  out = null;
        out = userFeignClient.insertFromHr(input);
        if(out==null || !out.isSuccess()){
            return null;
        }
        return out.getData();
    }

    /**
     * 获取子公司ID列表
     * @return
     */
    public List<String> selectOrgIdListById() {
        AjaxResponse<List<String>> feignResult = null;
        feignResult = userFeignClient.selectOrgIdListById();
        if(feignResult==null || !feignResult.isSuccess())
            return null;
        if(feignResult.getData()!=null) return feignResult.getData();
        else return new ArrayList<String>();

    }

    /**
     * 获取子公司及部门ID列表
     * @param orgId
     * @return
     */
    public List<String> selectChildIdListByOrgId(String orgId) {
        AjaxResponse<List<String>> feignResult = null;
        feignResult = userFeignClient.selectChildIdListByOrgId(orgId);
        if(feignResult==null || !feignResult.isSuccess())
            return null;
        if(feignResult.getData()!=null) return feignResult.getData();
        else return new ArrayList<String>();

    }

}
