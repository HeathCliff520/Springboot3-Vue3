package com.leecliff.springbootquickstart;

import com.leecliff.springbootquickstart.pojo.Emailproperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootQuickstartApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootQuickstartApplication.class, args);
        System.out.println(new Emailproperties().user);
    }

}
