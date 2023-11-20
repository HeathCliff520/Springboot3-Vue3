package com.leecliff.springbootquickstart.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class CommonImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //将多个第三方类注册为bean
        return new String[]{"ch.qos.logback.core.util.CloseUtil","ch.qos.logback.core.util.CharSequenceToRegexMapper"};
    }
}
