# Spring boot 自定义配置实现自动提示
> 这个是基于IDE来做的，Eclipse的没有试验过
            
## 1、新建一个配置类(ExampleProperties)
```
package org.yujuan.springbootlearning.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "example")
public class ExampleProperties {
    /**姓名**/
    private String name;
    /**路径**/
    private String path;
    /**是否开启*/
    private Boolean enable;
}
```
## 2、引入依赖spring-boot-configuration-processor
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.7.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>org.yujuan</groupId>
    <artifactId>springboot-learning</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot-learning</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.8</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```
## 3、修改IDE的配置
打开设置页面，按照箭头所指示的地方开启Enable annotation processing
![修改IDE配置](https://i.loli.net/2019/08/22/tiknTcr2md4lfKH.png)

## 4、重新编译
会在target目录下的META_INF 下生成spring-configuration-metadata.json文件，同时在配置文件写填写配置时，会自动提示。如下图：
![效果图](https://i.loli.net/2019/08/22/6YMgcjVyhWpFbEC.png)
![效果图](https://i.loli.net/2019/08/22/xRPbEdrtf86MLa9.png)
            
            
            
> 项目地址 [Github](https://github.com/yujuangithub0805/springboot-learning.git) springboot-configuation-auto-tips 分支

> 欢迎关注 [这个需求做不了](https://www.cnblogs.com/jakaBlog/) 
            
