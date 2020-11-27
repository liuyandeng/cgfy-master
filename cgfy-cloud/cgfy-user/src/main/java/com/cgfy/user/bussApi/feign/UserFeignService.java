package com.cgfy.user.bussApi.feign;


import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.base.bean.SelectInputBean;
import com.cgfy.user.base.bean.SelectOutputBean;
import com.cgfy.user.base.util.BaseCommonUtil;
import com.cgfy.user.bussApi.feign.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFeignService {

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 获取指定用户的用户信息
     * @param userid
     * @return
     */
    public UserInfoOutputBean getUserInfo(String userid) {
        AjaxResponse<UserInfoOutputBean> feignResult = null;
        feignResult = userFeignClient.getUserInfo(userid);
        if(feignResult==null || !feignResult.isSuccess())
            return null;
        return feignResult.getData();
    }

    /**
     * 获取当前登陆用户信息
     * @return
     */
    public UserInfoOutputBean getCurUserInfo() {
        // 登陆人ID
        String userId = BaseCommonUtil.getCurrentUserId();

        return this.getUserInfo(userId);
    }

    /**
     * 获取指定用户的用户信息
     * @param userid
     * @return
     */
    public UserInfoAllOutputBean getUserAllInfo(String userid) {
        AjaxResponse<UserInfoAllOutputBean> feignResult = null;
        feignResult = userFeignClient.getUserAllInfo(userid);
        if(feignResult==null || !feignResult.isSuccess())
            return null;
        return feignResult.getData();
    }

    /**
     * 获取当前登陆用户信息
     * @return
     */
    public UserInfoAllOutputBean getCurUserAllInfo() {
        // 登陆人ID
        String userId = BaseCommonUtil.getCurrentUserId();

        return this.getUserAllInfo(userId);
    }

    public List<UserInfoOutputBean> getUserInfoList(SelectInputBean input) {
        AjaxResponse<SelectOutputBean<UserInfoOutputBean>> feignResult = null;

        feignResult = userFeignClient.select(input);
        if(feignResult==null || !feignResult.isSuccess())
            return null;
        if(feignResult.getData()!=null) return feignResult.getData().getRecords();
        else return new ArrayList<UserInfoOutputBean>();
    }


    //通过门户人事系统添加人员
    public SysUserInfoInternalOutputBean insertFromHr(SysUserInfoInsertInputBean input){
        AjaxResponse<SysUserInfoInternalOutputBean>  out = null;
        out = userFeignClient.insertFromHr(input);
        if(out==null || !out.isSuccess()){
            return null;
        }
        return out.getData();
    }
    //条件更新
    public Boolean updateUserAndPtOrg(SysUserAndPtOrgUpdateInputBean input,String id){
        AjaxResponse<Boolean>  out = null;
        out = userFeignClient.updateUserAndPtOrg(input,id);
        if(out==null || !out.isSuccess()){
            return null;
        }
        return out.getData();
    }

    /**
     * 用户信息检索
     * @param input
     * @return
     */
    public List<UserInfoOutputBean> select(SelectInputBean input) {
        AjaxResponse<SelectOutputBean<UserInfoOutputBean>> feignResult = null;
        feignResult = userFeignClient.select(input);
        if(feignResult==null || !feignResult.isSuccess())
            return null;
        if(feignResult.getData()!=null) return feignResult.getData().getRecords();
        else return new ArrayList<UserInfoOutputBean>();
    }
    /**
     * 通过主键ID获取机构详情
     * @param orgId
     * @return
     */
    public SysOrgInfoInternalOutputBean selectSysOrgInfo(String orgId) {
        AjaxResponse<SysOrgInfoInternalOutputBean> feignResult = null;
        feignResult = userFeignClient.getOrgInfoById(orgId);
        if(feignResult==null || !feignResult.isSuccess())
            return null;
        if(feignResult.getData()!=null) return feignResult.getData();
        else return new SysOrgInfoInternalOutputBean();

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
