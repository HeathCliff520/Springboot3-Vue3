package com.leecliff.springbootquickstart.pojo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component
//public class Emailproperties {
//    @Value("${email.user}")
//    public String user;
//}

@ConfigurationProperties(prefix = "email")
@Component
public class Emailproperties {
    //注：这里的属性名称要与.yml中email下的名称字段保持一致，否则无法自动注入
    public String user;
    public String code;
    public String host;
    public String auth;
}
