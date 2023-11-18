package com.leecliff.springbootquickstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController：把Hellocontroller类交给springboot做成一个控制类
public class Hellocontroller {
    //@RequestMapping("/hello")：把请求路径及参数个springboot并将请求内容给hello方法以便hello方法处理并给出返回结果
    @RequestMapping("/hello")
    public String hello(){
        return "hello is from tomcat sever";
    }
}
