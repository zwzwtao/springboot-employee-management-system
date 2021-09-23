package com.odas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        // 具体的业务
        if (StringUtils.hasText(username) && "123456".equals(password)) {
            // session保存了用户信息
            session.setAttribute("loginUser", username);
            // 这里把跳转的页面的url要更改一下，所以不能只是return "dashboard";
            // 否则页面url不会更改，这样做是为了隐藏路径请求，防止泄露，不过登录失败还是能看到
            // 注意这里不用重定向是main不进去的，重定向更像是一个再次请求
            // 并且redirect也能防止表单重复提交
            return "redirect:/main.html";
        } else {
            // 告诉用户，你登陆失败了
            model.addAttribute("msg", "用户名或者密码错误!");
            return "index";
        }
    }

}
