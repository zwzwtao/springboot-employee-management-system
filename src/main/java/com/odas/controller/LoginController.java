package com.odas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        // 具体的业务
        if (StringUtils.hasText(username) && "123456".equals(password)) {
            // 这里把跳转的页面的url要更改一下，所以不能只是return "dashboard";
            // 否则页面url不会更改，这样做是为了隐藏路径请求，防止泄露，不过登录失败还是能看到
            return "redirect:/main.html";
        } else {
            // 告诉用户，你登陆失败了
            model.addAttribute("msg", "用户名或者密码错误!");
            return "index";
        }
    }



}
