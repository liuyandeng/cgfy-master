package com.cgfy.socket.api.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping(value = "/")
    public String toIndex(Model model) {
        model.addAttribute("msg","欢迎来到我的世界");
        return "index";
    }


}

