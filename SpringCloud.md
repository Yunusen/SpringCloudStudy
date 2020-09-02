# SpringCloud

详见：https://www.yuque.com/yathch/bw4uuq/xgt4s7

### 微服务cloud整体聚合父工程Project

#### 父工程步骤

![image-20200722170025656](/typora-user-images/image-20200722170025656.png)

1.new project

![image-20200722170926897](/typora-user-images/image-20200722170926897.png)

2.聚合总父工程名字

![image-20200722171239526](/typora-user-images/image-20200722171239526.png)

3.Maven选版本，不要使用自带的maven工程

![image-20200722171310308](/typora-user-images/image-20200722171310308.png)

finish后选择自动导入

4.工程名字

![image-20200722173048145](/typora-user-images/image-20200722173048145.png)

5.字符编码

![image-20200722172027789](/typora-user-images/image-20200722172027789.png)

6.注解生效激活

![image-20200722172326155](/typora-user-images/image-20200722172326155.png)7.java编译版本选8

![image-20200722172530993](/typora-user-images/image-20200722172530993.png)

8.File Type过滤（可做可不做）

![image-20200722172912457](/typora-user-images/image-20200722172912457.png)



#### 父工程POM

```xml
<!--统一管理jar包版本-->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <lombok.version>1.18.10</lombok.version>
        <log4j.version>1.2.17</log4j.version>
        <mysql.version>8.0.18</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>2.1.1</mybatis.spring.boot.version>
    </properties>

    <!-- 子模块继承之后，提供作用：锁定版本+子module不用谢groupID和version  -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-project-info-reports-plugin</artifactId>
                <version>3.0.0</version>
            </dependency>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!--spring cloud 阿里巴巴-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--mysql-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
                <scope>runtime</scope>
            </dependency>
            <!-- druid-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>

            <!--mybatis-->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
            <!--junit-->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <!--log4j-->
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
```



**dependencyManagement**

![image-20200722175014821](/typora-user-images/image-20200722175014821.png)

![image-20200722175228060](/typora-user-images/image-20200722175228060.png)



**maven中跳过单元测试**

![image-20200722175528993](/typora-user-images/image-20200722175528993.png)

父工程创建完成执行mvn:install将父工程发布到仓库方便子工程继承



#### 支付模块构建

![image-20200722180607139](/typora-user-images/image-20200722180607139.png)

**微服务模块**

1.建module

​	创建完成后回父pom中看一眼是否存在

```xml
<modules>
        <module>cloud-provider-payment8001</module>
</modules>
```

2.改POM

​	payment模块pom

```xml
<dependencies>
        <!-- 包含了sleuth zipkin 数据链路追踪-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
        <dependency><!-- 引用自己定义的api通用包，可以使用Payment支付Entity -->
            <groupId>com.eiletxie.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <!--如果没写版本,从父层面找,找到了就直接用,全局统一-->
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

3.写YML

```yml
server:
  port: 8001


spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  # 当前数据源操作类型
    driver-class-name: com.mysql.jdbc.Driver # org.gjt.mm.mysql.Driver 较老的写法，不通用  # mysql驱动包
    url: jdbc:mysql://localhost:3306/cloud2020?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: root

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.yunusen.springcloud.entites   # 所有Entity别名类所在包
```

4.主启动

```java
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }

}
```

5.业务类

1) 建表SQL

```sql
# TABLE payment
CREATE TABLE payment(
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID' PRIMARY KEY,
	serial varchar(200) DEFAULT ''	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
```

2)entities

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;

    private String serial;
}
```

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String message;

    private T data;

    public CommonResult(Integer code, String message){
        this(code, message, null);// 报错在setting->pluigns中安装lombok，重启idea
    }
}
```

3)dao

```java
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPayment(@Param("id") Long id);
}
```

在resources中Jmapper文件包，编写PaymentMapper

```xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.yunusen.springcloud.dao.PaymentDao">
    <insert id="create" parameterType="Payment" useGeneratedKeys="true" keyProperty="id">
        insert into payment(serial) values (#{serial});
    </insert>

    <resultMap id="BaseResultMap" type="com.yunusen.springcloud.entites.Payment">
        <id column="id" property="id" jdbcType="BIGINT"></id>
        <id column="serial" property="serial" jdbcType="VARCHAR"></id>
    </resultMap>
    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        select * from payment where id=#{id};
    </select>
</mapper>
```

4)service

```java
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
```

```java
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
```

5)controller

```java
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("**插入结果：" + result);

        if (result > 0){
            return new CommonResult(200, "插入数据库成功", result);
        }else{
            return new CommonResult(444,"插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("**查询结果：" + payment);

        if (payment != null){
            return new CommonResult(200, "查询成功", payment);
        }else{
            return new CommonResult(444,"没有对应记录，查询ID" + id, null);
        }
    }
}
```

启动，get请求直接网页测试，post请求使用postman测试



#### 热部署

子工程

```xml
<!--热部署-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

父工程

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
```

![image-20200723135045408](/typora-user-images/image-20200723135045408.png)

shift+ctrl+alt+/选择registry设置

![image-20200723135345602](/typora-user-images/image-20200723135345602.png)

![image-20200723135439833](/typora-user-images/image-20200723135439833.png)



#### 消费者模块构建

1.建model

2.改POM

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

3.写YML

```xml
server:
  port: 80
```

4.主启动

5.业务类

​	RestTemplate:提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的==客户端模板工具集==。

6.测试

如果404了可能是热部署问题，重启服务器即可



#### 工程重构

POM依赖

```xml
<dependencies>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.1.0</version>
        </dependency>
    </dependencies>
```

构建新模块cloud-api-commons，把重复使用的类（例：entites）放到该模块中，其他使用重复类的模块pom中添加

```xml
<!--引用自己定义的api通用包，可以使用Payment支付Entity-->
        <dependency>
            <groupId>com.yunusen</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
```



#### 服务注册中心Eureka模块

cloud-eureka-server7001

![image-20200805224013368](/typora-user-images/image-20200805224013368.png)

pom依赖

```xml
 <dependencies>
        <!--eureka-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!--引用自己定义的api通用包，可以使用Payment支付Entity-->
        <dependency>
            <groupId>com.yunusen</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>
```

application.yml

```yml
server:
  port: 7001

eureka:
  instance:
    hostname: localhost  # eureka服务端的实例名称
  client:
    # false表示不想注册中心注册自己
    register-with-eureka: false
    # false表示自己端就是注册中心，职责就是维护服务实例，不需要去检索服务
    fetch-registry: false
    service-url:
      # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

主启动添加@EnableEurekaServer

启动出现下图即eureka安装成功

![image-20200805203222107](/typora-user-images/image-20200805203222107.png)



微服务模块入驻eureka,在pom中添加依赖

```xml
<!--eureka-client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```

application.yml添加eureka配置

```yml
eureka:
  client:
    # 表示是否将自己注册进eureka-server，默认为true
    register-with-eureka: true
    # 是否从eureka-server抓取已有的注册信息，默认为true
    # 单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetch-registry: true
    service-url:
      # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://localhost:7001/eureka
```

主启动添加@EnableEurekaClient

在eureka-server启动情况下启动eureka-client，可看到有eureka有服务注册了

![image-20200805205422892](/typora-user-images/image-20200805205422892.png)



#### Eureka集群原理

![image-20200805225554337](/typora-user-images/image-20200805225554337.png)

建module，参考7001建7002eureka-server

修改映射配置添加进hosts文件C:\Windows\System32\drivers\etc\hosts

```
127.0.0.1    eureka7001.com
127.0.0.1    eureka7002.com
127.0.0.1    eureka7003.com
```

写yml,相互注册

```yml
server:
    port: 7001

eureka:
    instance:
        hostname: eureka7001.com  # eureka服务端的实例名称
    client:
        # false表示不想注册中心注册自己
        register-with-eureka: false
        # false表示自己端就是注册中心，职责就是维护服务实例，不需要去检索服务
        fetch-registry: false
        service-url:
            # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
            defaultZone: http://eureka7002.com:7002/eureka/
```

```yml
server:
    port: 7002

eureka:
    instance:
        hostname: eureka7002.com  # eureka服务端的实例名称
    client:
        # false表示不想注册中心注册自己
        register-with-eureka: false
        # false表示自己端就是注册中心，职责就是维护服务实例，不需要去检索服务
        fetch-registry: false
        service-url:
            # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
            defaultZone: http://eureka7001.com:7001/eureka/
```



##### 将订单支付两微服务注册进eureka集群

修改yml

```yml
eureka:
    client:
        # 表示是否将自己注册进eureka-server，默认为true
        register-with-eureka: false
        # 是否从eureka-server抓取已有的注册信息，默认为true
        # 单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
        fetch-registry: true
        service-url:
            # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
            #defaultZone: http://localhost:7001/eureka # 单机版
            defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka # eureka集群版
```



#### 支付微服务集群配置

建module，参考8001建8002，改主启动名

Order80Controller中端口改变,不在写死，通过服务器别名获取订单访问地址

```JAVA
public final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
```

在配置类中添加@LoadBalanced，使RestTemplate具有负载均衡的能力

```java
@Bean
@LoadBalanced
public RestTemplate getRestTemplate(){
    return new RestTemplate();
}
```

可在controller中获取端口号

```java
@Value("${server.port}")
private String serverPort;
```



#### actuator微服务信息完善

修改主机名称和显示ip地址，在application.yml中添加主机名称

```yml
server:
    port: 8001


spring:
    application:
        name: cloud-payment-service
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource  # 当前数据源操作类型
        driver-class-name: com.mysql.jdbc.Driver # org.gjt.mm.mysql.Driver 较老的写法，不通用  # mysql驱动包
        url: jdbc:mysql://localhost:3306/cloud2020?useUnicode=true&characterEncoding=utf-8&useSSL=false
        username: root
        password: root

eureka:
    client:
        # 表示是否将自己注册进eureka-server，默认为true
        register-with-eureka: true
        # 是否从eureka-server抓取已有的注册信息，默认为true
        # 单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
        fetch-registry: true
        service-url:
            # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
            #defaultZone: http://localhost:7001/eureka # 单机版
            defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka # eureka集群版
    # 主机名称
    instance:
        instance_id: payment8001
        prefer-ip-address: true  # 访问路径可以显示ip地址

mybatis:
    mapper-locations: classpath:mapper/*.xml
    type-aliases-package: com.yunusen.springcloud.entites   # 所有Entity别名类所在包
```



#### 服务发现Discovery

controller中使用DiscoveryClient

```java
 	@Resource
    private DiscoveryClient discoveryClient;
	@GetMapping(value = "/payment/discovery")
    private Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services){
            log.info("****element: " + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances){
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());

        }
        return this.discoveryClient;
    }
```

然后主启动类添加@EnableDiscoveryClient



#### Eureka自我保护机制（默认开启）

属于CAP里的AP分支

![image-20200806175907235](/typora-user-images/image-20200806175907235.png)

**在自我保护模式中，Eureka Server会保护服务注册表中的信息，不在注销任何服务实例**

如何禁止自我保护：

​		server7001yml配置：

```yml
server:
    port: 7001

eureka:
    instance:
        hostname: eureka7001.com  # eureka服务端的实例名称
    client:
        # false表示不想注册中心注册自己
        register-with-eureka: false
        # false表示自己端就是注册中心，职责就是维护服务实例，不需要去检索服务
        fetch-registry: false
        service-url:
            # 单机就是指向自己
            defaultZone: http://eureka7001.com:7001/eureka/
            # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
            # 集群指向其他eureka
            #defaultZone: http://eureka7002.com:7002/eureka/
    server:
        # 关闭自我保护机制，保护不可用服务及时剔除
        enable-self-preservation: false
        # 设置服务不可用后剔除间隔时间
        eviction-interval-timer-in-ms: 2000
```

​		payment8001yml配置：

```yml
server:
    port: 8001

spring:
    application:
        name: cloud-payment-service
    datasource:
        type: com.alibaba.druid.pool.DruidDataSource  # 当前数据源操作类型
        driver-class-name: com.mysql.jdbc.Driver # org.gjt.mm.mysql.Driver 较老的写法，不通用  # mysql驱动包
        url: jdbc:mysql://localhost:3306/cloud2020?useUnicode=true&characterEncoding=utf-8&useSSL=false
        username: root
        password: root

eureka:
    client:
        # 表示是否将自己注册进eureka-server，默认为true
        register-with-eureka: true
        # 是否从eureka-server抓取已有的注册信息，默认为true
        # 单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
        fetch-registry: true
        service-url:
            # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
            defaultZone: http://localhost:7001/eureka # 单机版
            #defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka # eureka集群版
    # 主机名称
    instance:
        instance_id: payment8001
        prefer-ip-address: true  # 访问路径可以显示ip地址
        
        #eureka自我保护禁止
        # Eureka客户端向服务端发送心跳的时间间隔，单位为秒（默认30秒）
        lease-renewal-interval-in-seconds: 1
        # Eureka服务器在收到最后一次心跳后等待时间上限，单位为秒（默认90秒），超时将剔除服务
        lease-expiration-duration-in-seconds: 2

mybatis:
    mapper-locations: classpath:mapper/*.xml
    type-aliases-package: com.yunusen.springcloud.entites   # 所有Entity别名类所在包


```



### Consul

**什么是consul**

![image-20200806202014156](/typora-user-images/image-20200806202014156.png)



### Eureka、Zookeeper、Consul的异同点

![image-20200806234105489](/typora-user-images/image-20200806234105489.png)



#### CAP

![image-20200806234848492](/typora-user-images/image-20200806234848492.png)

![image-20200806234430638](/typora-user-images/image-20200806234430638.png)

AP：Eureka

CP：Zookeeper、Consul



### Ribbon

**什么是Ribbon**

负载均衡+RestTemplate调用

![image-20200807000317374](/typora-user-images/image-20200807000317374.png)

**LB（LoadBalancer负载均衡）**

![image-20200807000643323](/typora-user-images/image-20200807000643323.png)

集中式LB

![image-20200807000840775](/typora-user-images/image-20200807000840775.png)

进程内LB

![image-20200807000918879](/typora-user-images/image-20200807000918879.png)



#### Rest调用

**getForObject方法和getForEntity方法**

getForObject：返回对象为响应体中的数据转化成的对象，基本可以理解为Json

getForEntity：返回对象为ResponseEntity对象，包含了响应中的一些重要信息，包括响应头，响应状态码，响应体等

![image-20200807112436297](/typora-user-images/image-20200807112436297.png)



报错：org.springframework.web.client.HttpClientErrorException$NotFound: 404 : [{"timestamp":"2020-08-07T03:45:12.121+0000","status":404,"error":"Not Found","message":"No message available","path":"/payment/get/2"}]

解决：8002没有方法，添加即可



**IRule**：根据特定算法中从服务列表中选取一个要访问的服务

<img src="/typora-user-images/image-20200807120309622.png" alt="image-20200807120309622" style="zoom:100%;" />

如何替换

![image-20200807120529933](/typora-user-images/image-20200807120529933.png)

自定义配置类不能放在@ComponentScan所扫描的当前包以及子包下，否则我们自定义的配置类会被所有而Ribbon客户端共享，达不到特殊化定制的目的了

在订单80模块自启动类包外新建一个包com.yunusen.myrule,新建规则类MySelfRule

```java
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();// 定义为随机
    }
}
```

主启动类添加注释@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)



#### Ribbon负载均衡算法

**原理**

![image-20200807154255583](/typora-user-images/image-20200807154255583.png)



**手写轮询算法**

支付8001和8002controller类中添加如下方法输出端口号

```java
@GetMapping("/payment/lb")
public String getPaymentLB(){
    return serverPort;
}
```

订单80config中注释掉@LoadBalanced，使用自定义算法

springcloud建自定义包lb，定义LoadBalancer接口

```java
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
```

实现类MyLB

```java
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;//Integer.MAX_VALUE=2147483647
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("**次数next: " + next);
        return next;
    }

    // 负载均衡算法：rest接口第几次请求树 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务器重启后rest接口从1开始计数
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
```

controller中加入方法测试

```java
@Resource
private LoadBalancer loadBalancer;

@Resource
private DiscoveryClient discoveryClient;

@GetMapping(value = "/consumer/payment/lb")
public String getPaymentLB(){
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    if (instances == null || instances.size() <= 0){
        return null;
    }

    ServiceInstance serviceInstance = loadBalancer.instances(instances);
    URI uri = serviceInstance.getUri();

    return restTemplate.getForObject(uri + "/payment/lb", String.class);

}
```



### OpenFeign

![image-20200808175125613](/typora-user-images/image-20200808175125613.png)

**Feign和OpenFeign的区别**

![image-20200808175237432](/typora-user-images/image-20200808175237432.png)



新建消费端module：cloud-consumer-feign-order80

pom依赖

```xml
<dependencies>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--    引入自定义api通用包    -->
        <dependency>
            <groupId>com.yunusen</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--eureka-client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

application.yml

```yml
server:
  	port: 80

eureka:
    client:
        # 表示是否将自己注册进eureka-server，默认为true
        register-with-eureka: false
        service-url:
            # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
            #defaultZone: http://localhost:7001/eureka # 单机版
            defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka # eureka集群版
```

主启动类添加@EnableFeignClients

业务类接口

```java
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}

```

controller类

```java
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
}
```



##### OpenFeign超时控制

**超时测试**

订单8001controller中添加超时方法timeout

```java
@GetMapping(value = "/payment/feign/timeout")
public String paymentFeignTimeout(){
    try {
        TimeUnit.SECONDS.sleep(3);
    }catch (InterruptedException e){
        e.printStackTrace();
    }
    return serverPort;
}
```

消费80service中添加timeout方法

```java
 @GetMapping(value = "/payment/feign/timeout")
 public String paymentFeignTimeout();
```

消费80controller添加timeout方法

```java
@GetMapping(value = "/consumer/payment/feign/timeout")
public String paymentFeignTimeout(){
    // openFeign-ribbon, 客户端一般默认等待一秒钟
    return paymentFeignService.paymentFeignTimeout();
}
```

报错：status 404 reading PaymentFeignService#paymentFeignTimeout()

原因：测试超时的方法只在一台服务提供者有，另一台没有，url不存在，造成了404

报错：java.net.SocketTimeoutException: Read timed out

原因：服务提供者方法超时，Feign默认1秒没有响应就超时报错

 **设置超时控制**

yml添加

```yml
# 设置feign客户端超时时间（OpenFeign默认支持ribbon）
ribbon:
    # 指的是建立连接所用的时间，适用于网络状况正常的情况下，两端连接所用的时间
    ReadTimeout: 5000
    # 指的是建立连接后从服务器读取到可用资源所用的时间
    ConnectTimeout: 5000
```



##### OpenFeign日志打印功能

添加一个config类

```java
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
```

yml配置

```yml
logging:
    level:
        # feign日志以什么级别监控哪个接口
        com.yunusen.springcloud.service.PaymentFeignService: debug
```





#### Hystrix



#### Gateway



#### Config



#### Bus



#### Stream

屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型

![image-20200818165324890](/typora-user-images/image-20200818165324890.png)

Stream中的消息通信方式遵循了发布-订阅模式–Topic模式进行广播（RabbitMQ中Exchange，Kafka中Topic）

![image-20200818174839482](/typora-user-images/image-20200818174839482.png)

![image-20200818175036691](/typora-user-images/image-20200818175036691.png)

建cloud-provider-payment8001生产者module

pom依赖

```xml
<dependencies>
        <dependency>
            <groupId>com.yunusen</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!--eureka-client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

yml

```yml
server:
  port: 8801

spring:
  application:
    name: cloud-stream-provider
  cloud:
    stream:
      binders: #在此处配置要绑定的rabbitmq的服务信息
        defaultRabbit: # 表示定义的名称，用于binding的整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: yunusen
                password: 123456
      bindings: # 服务的整合处理
        output: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          context-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain"
          bind er: defaultRabbit # 设置要绑定的消息服务的具体设置


eureka:
  client:
    service-url:
      # 设置与eureka server 交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://localhost:7001/eureka # 单机版
      #defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka # eureka集群版
    # 主机名称
  instance:
    instance_id: send8801.com
    prefer-ip-address: true  # 访问路径可以显示ip地址
    # Eureka客户端向服务端发送心跳的时间间隔，单位为秒（默认30秒）
    lease-renewal-interval-in-seconds: 2
    # Eureka服务器在收到最后一次心跳后等待时间上限，单位为秒（默认90秒），超时将剔除服务
    lease-expiration-duration-in-seconds: 5
```

service

```java
@EnableBinding(Source.class) // 定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;// 消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("***serial: " + serial);
        return null;
    }
}
```

controller

```java
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
```



建cloud-stream-rabbitmq-consumer8802消费者moduel

配置与8801一致，yml有改动output–>input

controller

```java
@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        log.info("消费者1号，接收到的信息：" + message.getPayload() + "\t" + "serverPort:" + serverPort);
    }
}

```

##### 分组消费和持久化

复制8802建module8803消费者

**存在重复消费问题**

原因：默认分组group是不同的，组流水号不一样，被认为不同组，可以消费

**自定义配置分组，分为同一个组，解决重复消费问题**

![image-20200818194911199](/typora-user-images/image-20200818194911199.png)

yml添加group，自定义分组,指定group即持久化

```yml
spring:
  application:
    name: cloud-stream-provider
  cloud:
    stream:
      binders: #在此处配置要绑定的rabbitmq的服务信息
        defaultRabbit: # 表示定义的名称，用于binding的整合
          type: rabbit # 消息组件类型
          environment: # 设置rabbitmq的相关的环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: yunusen
                password: 123456
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的Exchange名称定义
          context-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain"
          binder: defaultRabbit # 设置要绑定的消息服务的具体设置
          group: yunusen1
```



#### Sleuth:监控链路

```xml
<!--         包含了sleuth zipkin 数据链路追踪-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```

```yml
spring:
  application:
    name: cloud-payment-service
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
    # 采样率介于0到1之间，1则表示全部采集
      probability: 1
```

**Zipkin安装搭建：**

zipkin jar下载地址：https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/2.12.9/

安装命令：java -jar zipkin-server-2.12.9-exec.jar



### SpringCloud Alibaba

#### Nacos

Nacos：Dynamic Naming and Configuration Service

一个更易于构建云原生应用的动态服务发现，配置管理和服务管理凭条

Nacos就是注册中心+配置中心的组合

Nacos = Eureka + Config + Bus

下载地址：https://github.com/alibaba/nacos/releases/tag/1.1.4

解压运行startup.cmd启动，登录http://localhost:8848/nacos/，默认账号密码都为nacos

新建module cloudalibaba-provider-payment9001

pom添加nacos依赖

```java
<dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```

yml

```yml
server:
  port: 9001

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # 配置nacos地址

#检查端点全部打开
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

nacos官网：https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html



虚拟映射，指定端口：

![image-20200819162035285](/typora-user-images/image-20200819162035285.png)

![image-20200819162630478](/typora-user-images/image-20200819162630478.png)



##### nacos注册和负载

复制9001建9002

建module cloudalibaba-consumer-nacos-order83

pom和9001一致

yml

```yml
server:
  port: 83

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # 配置nacos地址

# 消费者将要去访问的微服务名称（注册成功进nacos的微服务提供者）
service-url:
  nacos-user-service: http://nacos-payment-provider

```



报错：java.net.UnknownHostException: nacos-payment-provider

原因：未在配置类添加@LoadBalanced



**服务中心对比：**

![image-20200819171807858](/typora-user-images/image-20200819171807858.png)

Nacos支持AP和CP模式的切换

![image-20200819172107404](/typora-user-images/image-20200819172107404.png)



##### Nacos服务配置中心

新建module cloudalibaba-config-nacos-client3377

pom

```xml
<dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--         引用自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.yunusen</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

bootstrap.yml

```yml
server:
  port: 3377

spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos服务注册中心地址
      config:
        server-addr: localhost:8848 # nacos作为配置中心地址
        file-extension: yaml # 指定yaml格式的配置
```

application.yml

```yml
spring:
  profiles:
    active: dev #表示开发环境
```

controller

```java
@RestController
@Slf4j
@RefreshScope // 支持nacos的动态刷新功能
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo(){
        return configInfo;
    }
}
```



nacos8848配置dataId格式

```
#${prefix}-${spring.profiles.active}.${file-extension}
#${spring.application.name}-${spring.profiles.active}.${file-extension}
#nacos-config-client-dev.yaml
```

![image-20200819175404685](/typora-user-images/image-20200819175404685.png)

报错：org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'scopedTarget.configClientController': Injection of autowired dependencies failed; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'config.info' in value "${config.info}"

原因：nacos配置文件改为yaml后缀



**DataID配置**

![image-20200819182328205](/typora-user-images/image-20200819182328205.png)

```
spring:
  profiles:
    #active: dev #表示开发环境
    active: test #表示测试环境
```

**Group配置**

![image-20200819182920751](/typora-user-images/image-20200819182920751.png)

在bootstrap.yml中添加spring.cloud.nacos.config.group: TEST_GROUP

**NameSpace空间方案**

![image-20200819183526788](/typora-user-images/image-20200819183526788.png)

在bootstrap.yml中添加spring.cloud.nacos.config.namespace: 37d60695-fbae-461d-9a00-e3f2929691b3 #dev

### 单机模式支持mysql，nacos默认数据库derby

在0.7版本之前，在单机模式时nacos使用嵌入式数据库实现数据的存储，不方便观察数据存储的基本情况。0.7版本增加了支持mysql数据源能力，具体的操作步骤：

- 1.安装数据库，版本要求：5.6.5+
- 2.初始化mysql数据库，数据库初始化文件：nacos-mysql.sql
- 3.修改conf/application.properties文件，增加支持mysql数据源配置（目前只支持mysql），添加mysql数据源的url、用户名和密码。

```
spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://11.162.196.16:3306/nacos_devtest?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true08:
db.user=nacos_devtest
db.password=youdontknow
```

再以单机模式启动nacos，nacos所有写嵌入式数据库的数据都写到了mysql

##### Nacos集群和持久化配置





#### Sentinel

下载地址：https://github.com/alibaba/Sentinel/releases

使用命令打开sentinel：java -jar sentinel-dashboard-1.7.2.jar

新建module cloudalibaba-sentinel-service8401

pom

```xml
<dependencies>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
<!--        持久化-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--         引用自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.yunusen</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

yml:注意格式

```yml
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos服务注册中心地址
    sentinel:
      transport:
        # 配置sentinel dashboard地址
        dashboard: localhost:8080
        # 默认端口。假如被占用会自动从8719开始依次+1扫描，直到找到未被占用等1端口
        port: 8719

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

发送请求测试



##### 流控规则

![image-20200824151330154](/typora-user-images/image-20200824151330154.png)

![image-20200824155837461](/typora-user-images/image-20200824155837461.png)

QPS直接失败：sentinel客户端设置qps

线程数直接失败：sentinel客户端设置线程数

关联失败：当该方法关联的另一个方法被限流了，该方法也被限流

预热：公式：阈值/冷加载因子coldFactor（默认值3），刚开始被限流，再规定时间内大道阈值，应用场景：秒杀设计

排队等待：让请求以均匀的速度通过，对应的是漏桶算法

链路：只记录链路路口的流量

```java
@Service
@Slf4j
public class OrderService {

    @SentinelResource("getOrder")
    public String getOrder(){
        log.info("==order==");
        return "order";
    }
}
```

```java
@RestController
@Slf4j
public class FlowLimitController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/testA")
    public String testA(){
        orderService.getOrder();
//        try{
//            TimeUnit.MILLISECONDS.sleep(800);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        return "---testA";
    }

    @GetMapping("/testB")
    public String testB(){
        orderService.getOrder();//鏈路调用
        //log.info(Thread.currentThread().getName()+ "\t" + "/testB");
        return "---testB";
    }
}
```

![image-20200824164956630](/typora-user-images/image-20200824164956630.png)

请求接口testB会调用getOrder进行流量控制，testA不会。

> 针对来源是微服务级别的，链路模式的入口资源是针对方法接口的。



##### 熔断降级

- 慢调用比例 (`SLOW_REQUEST_RATIO`)：选择以慢调用比例作为阈值，需要设置允许的慢调用 RT（即最大的响应时间），请求的响应时间大于该值则统计为慢调用。当单位统计时长（`statIntervalMs`）内请求数目大于设置的最小请求数目，并且慢调用的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求响应时间小于设置的慢调用 RT 则结束熔断，若大于设置的慢调用 RT 则会再次被熔断。
- 异常比例 (`ERROR_RATIO`)：当单位统计时长（`statIntervalMs`）内请求数目大于设置的最小请求数目，并且异常的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。异常比率的阈值范围是 `[0.0, 1.0]`，代表 0% - 100%。
- 异常数 (`ERROR_COUNT`)：当单位统计时长内的异常数目超过阈值之后会自动进行熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。



##### 热点规则

何为热点？热点即经常访问的数据。很多时候我们希望统计某个热点数据中访问频次最高的 Top K 数据，并对其访问进行限制。比如：

- 商品 ID 为参数，统计一段时间内最常购买的商品 ID 并进行限制
- 用户 ID 为参数，针对一段时间内频繁访问的用户 ID 进行限制

热点参数限流会统计传入参数中的热点参数，并根据配置的限流阈值与模式，对包含热点参数的资源调用进行限流。热点参数限流可以看做是一种特殊的流量控制，仅对包含热点参数的资源调用生效。

```java
// value为资源名，blockHandler为自定义限流方法
@GetMapping(value = "/testHotKey")
@SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
public String testHotKey(
    @RequestParam(value = "p1", required = false) String p1,@RequestParam(value = "p2", required = false) String p2){
        return "testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException ex){
        return "deal_testHotKey";
    }
```

![image-20200824232227533](/typora-user-images/image-20200824232227533.png)



##### 系统规则

系统保护规则是从应用级别的入口流量进行控制，从单台机器的 load、CPU 使用率、平均 RT、入口 QPS 和并发线程数等几个维度监控应用指标，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

系统保护规则是应用整体维度的，而不是资源维度的，并且**仅对入口流量生效**。入口流量指的是进入应用的流量（`EntryType.IN`），比如 Web 服务或 Dubbo 服务端接收的请求，都属于入口流量。

系统规则支持以下的模式：

- **Load 自适应**（仅对 Linux/Unix-like 机器生效）：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR 阶段）。系统容量由系统的 `maxQps * minRt` 估算得出。设定参考值一般是 `CPU cores * 2.5`。
- **CPU usage**（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏。
- **平均 RT**：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
- **并发线程数**：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
- **入口 QPS**：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。



##### @SentinelResource注解

> 注意：注解方式埋点不支持 private 方法。

`@SentinelResource` 用于定义资源，并提供可选的异常处理和 fallback 配置项。 `@SentinelResource` 注解包含以下属性：

- `value`：资源名称，必需项（不能为空）
- `entryType`：entry 类型，可选项（默认为 `EntryType.OUT`）
- `blockHandler` / `blockHandlerClass`: `blockHandler` 对应处理 `BlockException` 的函数名称，可选项。blockHandler 函数访问范围需要是 `public`，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 `BlockException`。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `blockHandlerClass` 为对应的类的 `Class` 对象，注意==对应的函数必需为 static 函数==，否则无法解析。
- fallback/fallbackClass：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了exceptionsToIgnore里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
  - 返回值类型必须与原函数返回值类型一致；
  - 方法参数列表需要和原函数一致，或者可以额外多一个 `Throwable` 类型的参数用于接收对应的异常。
  - fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `fallbackClass` 为对应的类的 `Class` 对象，注意==对应的函数必需为 static 函数==，否则无法解析。
- defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了exceptionsToIgnore里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
  - 返回值类型必须与原函数返回值类型一致；
  - 方法参数列表需要为空，或者可以额外多一个 `Throwable` 类型的参数用于接收对应的异常。
  - defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `fallbackClass` 为对应的类的 `Class` 对象，注意==对应的函数必需为 static 函数==，否则无法解析。
- `exceptionsToIgnore`（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。



##### Sentinel服务熔断Ribbon

```java
public static final String SERVICE_URL = "http://nacos-payment-provider";

@Resource
private RestTemplate restTemplate;

/*
    @SentinelResource(value = "fallback")//没有配置
    @SentinelResource(value = "fallback", fallback = "handlerFallback")//fallback只负责业务异常
    @SentinelResource(value = "fallback", blockHandler = "blockHandler")// blockHandler只负责sentinel控制台配置异常
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
 */
@RequestMapping("/consumer/fallback/{id}")
@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler", exceptionsToIgnore = IllegalAccessException.class)
public CommonResult<Payment> fallback(@PathVariable Long id) throws IllegalAccessException {
    CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
    if (id == 4){
        throw new IllegalAccessException("IllegalAccessException, 非法参数异常");
    }else if(result.getData() == null) {
        throw new NullPointerException("NullPointerException, 该id无对应记录，空指针异常");
    }
    return result;
}

public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable throwable){
    Payment payment = new Payment(id, null);
    return new CommonResult<>(444, "handlerException兜底异常，内容：" + throwable.getMessage(), payment);
}

public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException ex){
    Payment payment = new Payment(id, null);
    return new CommonResult<>(445, "blockHandler-sentinel限流，内容：" + ex.getMessage(), payment);
}
```

##### Sentinel服务熔断OpenFeign

yml

```yml
feign:
  sentinel:
    enabled: true
```

```java
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id")Long id);
}
```

```java
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回，--PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
```

controller

```java
// openFeign
@Resource
private PaymentService paymentService;

@GetMapping(value = "/consumer/paymentSQL/{id}")
public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
    return paymentService.paymentSQL(id);
};
```

##### sentinel持久化规则

```xml
<!--        持久化-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
```

yml

```yml
spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos服务注册中心地址
    sentinel:
      transport:
        # 配置sentinel dashboard地址
        dashboard: localhost:8080
        # 默认端口。假如被占用会自动从8719开始依次+1扫描，直到找到未被占用等1端口
        port: 8719
      datasource:
        ds1:
          nacos:
            server-addr: localhost:8848
            dataId: cloudalibaba-sentinel-service
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: flow
```

nacos客户端配置

![image-20200828190516920](/typora-user-images/image-20200828190516920.png)

```json
[
    {
        "resource": "/rateLimit/byUrl",
        "limitApp": "default",
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
```

resource：资源名称

limitApp：来源应用

grade：阈值类型，0表示线程数，1表示QPS

count：单机阈值

strategy：流控模式，0表示直接，1表示关联，2表示链路

controlBehavior：流控效果，0表示快速失败，1表示Warm Up，2表示排队等待

clusterMode：是否集群



#### Seata处理分布式事务

Seate是一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务。

官网地址：http://seata.io/zh-cn/

分布式事务处理过程的一ID+三组件模型（全局唯一的事务ID+seata术语三组件）

##### Seata术语

 **TC (Transaction Coordinator) - 事务协调者**

维护全局和分支事务的状态，驱动全局事务提交或回滚。

 **TM (Transaction Manager) - 事务管理器**

定义全局事务的范围：开始全局事务、提交或回滚全局事务。

 **RM (Resource Manager) - 资源管理器**

管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。

![image-20200828225154102](/typora-user-images/image-20200828225154102.png)



下载地址：https://github.com/seata/seata/releases/tag/v0.9.0

1.修改file.conf文件，先备份在修改

修改：自定义事务组名称+事务日志存储模式为db+数据库连接信息

service模块：

​	vgroup_mapping.my_test_tx_group = "yunusen_tx_group"

store模块：

​	mode = "db"

db 模块：
    url = "jdbc:mysql://127.0.0.1:3306/seata"
    user = "root"
    password = "root"

2.mysql新建库seata

3.在seata库建表：建表db_store.sql在conf里面,直接在seata库中运行

4.修改conf里面的registry.conf配置文件

registry模块：

​	type = "nacos"

nacos模块：

​    serverAddr = "localhost:8848"

5.先启动nacos，后启动seata

6.创建业务数据库：

​	seata_order：存储订单的数据库

​	seata_storage：存储库存的数据库

​	seata_account：存储账户信息的数据库

7.在各个数据库下建立业务表：

seata_order建立t_order表：

```sql
CREATE TABLE t_order(
	id BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT(11) DEFAULT NULL COMMENT '用户id',
    product_id BIGINT(1) DEFAULT NULL COMMENT '产品id',
    count INT(11) DEFAULT NULL COMMENT '数量',
    money DECIMAL(11,0) DEFAULT NULL COMMENT '金额',
    status INT(1) DEFAULT NULL COMMENT '订单状态: 0:创建中，1:已完结'  
)ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8
```

seata_storage建立t_storage表：

```sql
CREATE TABLE t_storage(
	id BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT(1) DEFAULT NULL COMMENT '产品id',
    total INT(11) DEFAULT NULL COMMENT '总库存',
    used INT(11) DEFAULT NULL COMMENT '已用库存',
    residue INT(11) DEFAULT NULL COMMENT '剩余库存'
)ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

INSERT INTO t_storage(id,product_id,total,used,residue) 
VALUES(1,1,100,0,100);
```

seata_account建立t_account表：

```sql
CREATE TABLE t_account(
	id BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT(11) DEFAULT NULL COMMENT '用户id',
    total INT(11) DEFAULT NULL COMMENT '总额度',
    used INT(11) DEFAULT NULL COMMENT '已用余额',
    residue INT(11) DEFAULT NULL COMMENT '剩余可用额度'
)ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

INSERT INTO t_account(id,user_id,total,used,residue) 
VALUES(1,1,1000,0,1000);
```

三个业务库建立日志回滚undo_log表，sql在conf中db_undo_log.sql,复制运行即可

8.新建module seata-order-service2001

pom依赖

```xml
<dependencies>
        <!--nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--seata-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>seata-all</artifactId>
                    <groupId>io.seata</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-all</artifactId>
            <version>0.9.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--         引用自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.yunusen</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
            <!--如果没写版本,从父层面找,找到了就直接用,全局统一-->
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

yml

```yml
server:
  port: 2001

spring:
  application:
    name: seata-order-service
  cloud:
    alibaba:
      seata:
        #自定义事务组名称需要与seata-server中的对应
        tx-service-group: yunusen_tx_group
    nacos:
      discovery:
        server-addr: localhost:8848
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/seata_order
    username: root
    password: root

feign:
  hystrix:
    enabled: false

logging:
  level:
    io:
      seata: info

mybatis:
  mapperLocations: classpath:mapper/*.xml
```

9.复制conf下file.conf，registry.conf到项目resources下

10.建domain包，填写基本类

CommonResult

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
```

Order

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    private Integer status;// 订单状态： 0：创建中，1：已完结
}
```

11.Dao接口及实现类

```java
@Mapper
public interface OrderDao {

    // 1.新建订单
    void create(Order order);

    // 2.修改订单状态，从0改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
```

```xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.yunusen.springcloud.alibaba.dao.OrderDao">

    <resultMap id="BaseResultMap" type="com.yunusen.springcloud.alibaba.domain.Order">
        <id column="id" property="id" jdbcType="BIGINT"></id>
        <result column="user_id" property="userId" jdbcType="BIGINT"></result>
        <result column="product_id" property="product_id" jdbcType="BIGINT"></result>
        <result column="count" property="count" jdbcType="INTEGER"></result>
        <result column="money" property="money" jdbcType="DECIMAL"></result>
        <result column="status" property="status" jdbcType="INTEGER"></result>
    </resultMap>
    
    <insert id="create">
        insert into t_order(id, user_id, product_id, count, money, status)
        values (null, #{userId}, #{product_id}, #{count}, #{money}, 0);
    </insert>

    <update id="update">
        update t_order set status=1 where user_id=#{userId} and status=#{status};
    </update>
</mapper>
```

```java
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 下订单-->减库存-->减余额-->改状态
     * @param order
     */
    @Override
    public void create(Order order) {
        log.info("-->开始新建订单");
        // 1.新建订单
        orderDao.create(order);

        log.info("-->订单微服务开始调用库存，做扣减count");
        // 2.扣减库存
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("-->订单微服务开始调用库存，做扣减end");

        log.info("-->订单微服务开始调用账户，做扣减money");
        // 3.扣减账户余额
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("-->订单微服务开始调用账户，做扣减end");

        // 4.修改订单的状态，从0到1,1代表已经完成
        log.info("-->修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("-->修改订单状态结束");

        log.info("-->订单结束");

    }
}}
```

controller

```java
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

}
```

config

```java
package com.yunusen.springcloud.alibaba.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 * @author yunusen
 * @version 1.0
 * @date 2020/8/29 3:08
 * 使用seata对数据源进行代理
 */
@Configuration
public class DataSourceProxyConfig {

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource){
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }
}
```

```java
@Configuration
@MapperScan({"com.yunusen.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
```

主启动类

```java
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)// 取消数据源的自动配置
public class SeataOrderMain2001 {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain2001.class, args);
    }
}
```

依样对seata-storage-service2002，seata-account-service2003进行编写



报错：feign.FeignException$MethodNotAllowed: status 405 reading StorageService#decrease(Long,Integer)

解决：@PostMapping该为@GetMapping



报错：feign.FeignException$InternalServerError: status 500 reading StorageService#decrease(Long,Integer)

解决：sql字段拼写错误



报错：java.net.SocketTimeoutException: Read timed out

解决：feign.client.config.default.connect-timeout=300000

feign.client.config.default.read-timeout=300000



```java
//业务类方法加上,保证事务一致性
@GlobalTransactional(name = "yunusen-create-order", rollbackFor = Exception.class)
```



##### 分布式事务执行流程

![image-20200830001341794](/typora-user-images/image-20200830001341794.png)

一阶段得到前镜像和后镜像，二阶段回滚（有数据校验）或者提交

详见官网：http://seata.io/zh-cn/docs/overview/what-is-seata.html