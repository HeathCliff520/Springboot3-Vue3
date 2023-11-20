package com.leecliff.springbootquickstart.controller;

import com.leecliff.springbootquickstart.pojo.User;

import com.leecliff.springbootquickstart.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //将类加载到spring容器，交给spring统一管理
public class userController {
    @Autowired //自动使用service接口赋值
    private userService userSer;

    @RequestMapping("/findByid") //springboot生成访问路径
    public User findById(Integer idfromBrownser) {
        return userSer.findById(idfromBrownser);
    }
}
