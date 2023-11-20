# Springboot3-Vue3
黑马听课笔记实验

```md
springboot是springFramework框架
的一个子项目(springDate，springamqp,
sprngsecurity,springcloud)
```
## Springboot-quickstart
### springboot项目创建：
1. 创建maven工程
2. 导入spring-boot-starter-web依赖(porm.xml中)
3. 编写Controller(提供用户请求所要返回内容的方法)
      1. 要给出请求内容
      2. 写出对请求内容管理的函数
      ```
      @RestController：把java类交给springboot做成一个控制类
      @RequestMapping("/hello")：把请求路径及参数交給springboot并将请求内容给某个方法以便该方法处理并给出返回结果
      ```
4. 提供启动类
### springboot项目配置：
**配置文件：../src/main/resources/application.properties或者application.yml两种**
1. [.properties配置](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
2. [.yml配置]
   ```yaml
   comment:
        "如下配置"
   server:
     port:
     servlet:
       context-path:
   ```