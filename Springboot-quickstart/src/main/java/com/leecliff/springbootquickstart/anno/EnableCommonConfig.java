package com.leecliff.springbootquickstart.anno;

import com.leecliff.springbootquickstart.config.CommonImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CommonImportSelector.class)
public @interface EnableCommonConfig {//自定义组合注解以供启动了使用
}
