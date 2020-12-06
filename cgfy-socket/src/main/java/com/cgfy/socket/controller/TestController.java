package com.cgfy.socket.controller;
import com.cgfy.socket.bean.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class TestController {
    @GetMapping(value = "/")
    public String toIndex(Model model) {
        List<UserInfo> userList=new ArrayList();
        UserInfo user1 =new UserInfo();
        user1.setId("001");
        user1.setName("张三");
        user1.setAge("20");
        user1.setSex("男");
        user1.setAddress("河南");
        UserInfo user2 =new UserInfo();
        user2.setId("002");
        user2.setName("李四");
        user2.setAge("25");
        user2.setSex("女");
        user2.setAddress("山西");
        UserInfo user3 =new UserInfo();
        user3.setId("003");
        user3.setName("王五");
        user3.setAge("30");
        user3.setSex("未知");
        user3.setAddress("河北");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        model.addAttribute("msg","欢迎来到我的世界!!!");
        model.addAttribute("flag",true);
        model.addAttribute("date",new Date());
        model.addAttribute("userList",userList);
        return "index";
    }

    /**
     * 测试提交
     * @param user
     * @return
     */
    @PostMapping(value = "/testSubmit")
    @ResponseBody
    public Map testSubmit(UserInfo user) {
        Map map=new HashMap();
        System.out.println(user.getName());
        map.put("msg",user.getName());
        return map;
    }

}

