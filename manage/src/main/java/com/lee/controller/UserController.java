package com.lee.controller;

import com.alibaba.druid.util.Base64;
import com.lee.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lzz
 * @Description:
 * @Date: Created in 9:58 2018/8/14
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req) {
        logger.debug("Received request params.{[],[]}",username, password);
        String pw = Base64.byteArrayToAltBase64(password.getBytes());
        Base64.altBase64ToByteArray(pw).toString();
        userService.login(username,password);
        return new ModelAndView("manage/userPage");
    }


    @RequestMapping("/init")
    void init(){
        logger.debug("开始初始化！");
        userService.init("123456");
    }

}
