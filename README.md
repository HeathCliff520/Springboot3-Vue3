# Springboot3-Vue3

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
2. [.yml配置]：涉及三方技术配置信息，自定义配置信息等
   ```yaml
   server:
      port: 9090
         
   #email.user,email.code,email.host,email.auth
   #缩进一格表示讲一级
   email:
      user:  li  #注意冒号与值之间有空格，否则报错
      code:
      host:
      auth:
   #数组配置：hobbies[3]={data01,data02,data03}
   hobbies:
     - data01
     - data02
     - data03

3. 获取`.yml`中的数据：`../springbootquickstart/pojo/Emailproperties.java`
   1. 第一种使用`@Component`与`@Value`完成`.yml`中的值获取
   ```java
   @Component //使用@Component使实体类成为bean
   public class Emailproperties {
       @Value("${email.user}") //使用@Value加载yml中的数据
       public String user;
   }
   ```
   2. 第二种使用 `@ConfigurationProperties`获取`.yml`中各字段的值
   ```java
    @ConfigurationProperties(prefix = "email")
    @Component
    public class Emailproperties {
        //注：这里的属性名称要与.yml中email下的名称字段保持一致，否则无法自动注入
        public String user;
        public String code;
        public String host;
        public String auth;
    }
    ```
4. springboot整合第三方资源，以mybatis为例:
   1. pom中导入资源坐标：`../Springboot-quickstart/pom.xml`
   ```pom
      <dependency>
         <groupId>com.mysql</groupId>
         <artifactId>mysql-connector-j</artifactId>
      </dependency>
      <dependency>
         <groupId>org.mybatis.spring.boot</groupId>
         <artifactId>mybatis-spring-boot-starter</artifactId>
         <version>3.0.2</version>
      </dependency>
   ```
   2. 在`.yml`中对该资源进行配置
   ```yaml
   #整合第三方(mybatis) 
   spring:
    datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       url: jdbc:mysql://localhost:3306/databasename
       username:
       password:
   ```
   3. 在实体类中使用配置好第三方资源
      1. 实体类(pojo中，java实现的数据库中存放的数据模型)
      2. 业务流程：cotroller调用service，service调用mapper
         ```java
         //1.实现pojo类(java类-数据库模型): `../springbootquickstart/pojo/User.java`
              public class User {
                  private Integer id;
                  private String name;
                  private Short age;
                  private Short gender;
              }
         //2.编写对应的controller: `..springbootquickstart/controller/userController.java`
              @RestController //将类加载到spring容器，交给spring统一管理
              public class userController {
                   @Autowired //自动使用service接口赋值
                   private userService userSer;
         
                   @RequestMapping("/findByid") //springboot生成访问路径
                   public User findById(Integer idfromBrownser) {
                       return userSer.findById(idfromBrownser);
                   }
              }
         //3.编写service：`../springbootquickstart/service/impl/userServiceimpl.java`
               @Service //使java类成为service组建，交由springboot管理
               public class userServiceimpl implements userService {
                   @Autowired //service调用mapper，使用自动注入，将mapper作为service的成员变量
                   private userMapper usmp;
         
                   @Override //重写service接口方法
                   public User findById(Integer idfromBrowser) {
                       return usmp.findById(idfromBrowser);
                   }
               }
         //4.编写mapper：`../springbootquickstart/mapper/userMapper.java`
               @Mapper //使类成为springboot的mapper组件
               public interface userMapper {
                     //将浏览器的值交给数据库进行查询
                   @Select("select * from user where id = #{idfromBrowser}")
                   public User findById(Integer idfromBrowser);
               }
### springboot管理第三方jar包使之成为Bean
`注意：不可以同时使用多个方法对同一个类进行注册，否则出错`
   1. 直接在启动类中注册
      ```java
      @SpringBootApplication //使spring可以自动识别本级及其下级已添加注解的bean的包和路径
      public class SpringbootQuickstartApplication {
      
          public static void main(String[] args) {
             ApplicationContext context = SpringApplication.run(SpringbootQuickstartApplication.class, args);
             System.out.println("这是一个三方类被注册成了bean："+context.getBean(CloseUtil.class));
          }
      
          @Bean //Bean注解将第三方类CloseUtil通过regiasterCloseUtil方法的返回值注册成bean对象
          public CloseUtil regiasterCloseUtil(){//该方法不推荐，因为这是在启动类中完成的注册
              return new CloseUtil();
          }
      
      }
      ```


   2. 通过配置类完成第三方类注册：../springbootquickstart/config/CommonCofig.java
      ```java
         package com.leecliff.springbootquickstart.config;
         @Configuration //使java类成为配置类，以完成第三方类的bean注册
         public class CommonCofig {
            //注册好的bean在容器中的名称默认是该方法名称，即容器中叫做registerCloseUtil，通过`@Bean("beanname")`可以改名
            @Bean//Bean注解将第三方类CloseUtil通过regiasterCloseUtil方法的返回值注册成bean对象
            public CloseUtil registerCloseUtil(){
                   return new CloseUtil();
            }
         }
         System.out.println(context.getBean("registerCloseUtil"));//在启动类中测试
      ```

   3. 在启动类上通过@import(xxx.class)注册第三方类
      1. 单个注册
      ```java
         @Import(CloseUtil.class)
         @SpringBootApplication //使spring可以自动识别本级及其下级已添加注解的bean的包和路径
         public class SpringbootQuickstartApplication {
             public static void main(String[] args) {
                ApplicationContext context = SpringApplication.run(SpringbootQuickstartApplication.class, args);
                System.out.println("这是一个三方类被注册成了bean："+context.getBean(CloseUtil.class));
             }
         }
      ```
      2. 注册多个
      ```java
         //config包中实现ImportSelector接口
         package com.leecliff.springbootquickstart.config;
         public class CommonImportSelector implements ImportSelector {
              @Override
              public String[] selectImports(AnnotationMetadata importingClassMetadata) {
                   //将多个第三方类注册为bean
                   return new String[]{"ch.qos.logback.core.util.CloseUtil","ch.qos.logback.core.util.CharSequenceToRegexMapper"};
              }
         }
      
         //启动类中    
         @Import(CommonImportSelector)
         @SpringBootApplication //使spring可以自动识别本级及其下级已添加注解的bean的包和路径
         public class SpringbootQuickstartApplication {
             public static void main(String[] args) {
                ApplicationContext context = SpringApplication.run(SpringbootQuickstartApplication.class, args);
                System.out.println("这是一个三方类被注册成了bean："+context.getBean(CloseUtil.class));
             }
         }
      ```
      3. 通过外部配置文件注册多个第三方类成为bean
         1. 建文件`../resources/common.imports`存放多个类路径名
            ```text
            //一行放一个类
             ch.qos.logback.core.util.CloseUtil
             ch.qos.logback.core.util.CharSequenceToRegexMapper
            ```
         2. 实现`ImportSelector`接口
              ```java
              package com.leecliff.springbootquickstart.config;
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
            ```
         3. 在启动类中通过自定义组合注解`@EnableCommonConfig`使用注册好的第三方bean
            ```java
                package com.leecliff.springbootquickstart.anno;
                @Target(ElementType.TYPE)
                @Retention(RetentionPolicy.RUNTIME)
                @Import(CommonImportSelector.class)
                public @interface EnableCommonConfig {//自定义组合注解以供启动了使用
                }
            //注册类中调用
                
                
            ```