package com.leecliff.springbootquickstart.config;

import ch.qos.logback.core.util.CloseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //使类成为配置类，以完成第三方类的bean注册
public class CommonCofig {
    @Bean
    public CloseUtil registerCloseUtil(){
        return new CloseUtil();
    }
}

