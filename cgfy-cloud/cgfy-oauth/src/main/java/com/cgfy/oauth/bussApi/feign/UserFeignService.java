package com.cgfy.oauth.bussApi.feign;


import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.bussApi.feign.bean.UserInfoOutputBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeignService {
    @Autowired
    private UserFeignClient userFeignClient;
    //通过门户人事系统添加人员
    public UserInfoOutputBean getDetail(String id){
        AjaxResponse<UserInfoOutputBean> out = null;
        out = userFeignClient.getDetail(id);
        if(out==null || !out.isSuccess()){
            return null;
        }
        return out.getData();
    }
}
