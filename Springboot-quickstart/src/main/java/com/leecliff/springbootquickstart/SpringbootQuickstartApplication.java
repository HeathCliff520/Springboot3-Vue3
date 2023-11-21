package com.leecliff.springbootquickstart;

import ch.qos.logback.core.util.CloseUtil;
import com.leecliff.springbootquickstart.anno.EnableCommonConfig;
import com.leecliff.springbootquickstart.config.CommonImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
//单个注册
//@Import(CloseUtil.class)
//多个注册，通过实现ImportSelector接口完成多个注册
//@Import(CommonImportSelector.class)
@SpringBootApplication //使spring可以自动识别本级及其下级已添加注解的bean的包和路径
@EnableCommonConfig
public class SpringbootQuickstartApplication {

    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(SpringbootQuickstartApplication.class, args);
        //当前使用bean注册的，不要打开@Import再次注册，否则报错
        System.out.println("这是一个三方类被注册成了bean："+context.getBean(CloseUtil.class));
    }
}
