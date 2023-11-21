package com.leecliff.springbootquickstart.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommonImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //将多个第三方类注册为bean
        //return new String[]{"ch.qos.logback.core.util.CloseUtil"
        // ,"ch.qos.logback.core.util.CharSequenceToRegexMapper"};
        List<String> importsList=new ArrayList<>();
        //通过流读取外部资源
        InputStream resourceStream = CommonImportSelector.class
                .getClassLoader()
                .getResourceAsStream("common.imports");
        BufferedReader br=new BufferedReader(new InputStreamReader(resourceStream));
        String line =null;
        try {
            while ((line= br.readLine())!=null){
                importsList.add(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return importsList.toArray(new String[0]);
    }
}
