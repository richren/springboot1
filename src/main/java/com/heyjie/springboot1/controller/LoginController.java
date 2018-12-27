package com.heyjie.springboot1.controller;

import org.apache.catalina.connector.Request;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.spring5.expression.Mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(value="/index",method=RequestMethod.GET)
    public String index()
    {
        //spring security会在表单提交之后自动调用MySecurityConfig的configure(AuthenticationManagerBuilder auth)
        //根据用户名获取用户信息，然后自动判断密码正确性，如果正确会自动session中报错登录信息
        //不需要自己写处理逻辑
        return "login/index";
    }

    @RequestMapping("/logout")
    public String logout()
    {
        //因为在MySecurityConfig中配置了.logoutUrl("/login/logout")
        //所以当正确访问"/login/logout"后就会退出，不用写额外代码（貌似测试的时候session没有清理掉，所以加上了下面的代码）
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            HttpServletResponse response = requestAttributes.getResponse();
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }*/
        return "login/logout";
    }

    @RequestMapping(value="/error",method=RequestMethod.GET)
    public String error(Model model)
    {
        model.addAttribute("hasErrorMsg", true);
        return "login/index";
    }

    @RequestMapping(value="/home",method=RequestMethod.GET)
    public String home(Model model )
    {
        SecurityContext secCtx = SecurityContextHolder.getContext();//获取当前登录用户信息
        Authentication auth = secCtx.getAuthentication();
        model.addAttribute("username",auth.getName());
        return "login/home";
    }
}

