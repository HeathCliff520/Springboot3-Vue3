package com.leecliff.springbootquickstart.service.impl;

import com.leecliff.springbootquickstart.mapper.userMapper;
import com.leecliff.springbootquickstart.pojo.User;
import com.leecliff.springbootquickstart.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //使java类成为service组建，交由springboot管理
public class userServiceimpl implements userService {
    @Autowired //service调用mapper，使用自动注入，将mapper作为service的成员变量
    private userMapper usmp;

    @Override //重写service接口方法
    public User findById(Integer idfromBrowser) {
        return usmp.findById(idfromBrowser);
    }
}
