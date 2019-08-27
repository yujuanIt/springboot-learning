## 1、编写自定义配置文件custom.propertis，并放到resource目录下
```
file.size=1111
```
![custom.propertis 文件](https://i.loli.net/2019/08/28/kRIm1TsrHMliQX5.png)

## 2、编写自定义的加载类CustomEnvironmentPostProcessor,实现EnvironmentPostProcessor接口,重写postProcessEnvironment方法
```

package org.yujuan.springbootlearning.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * The type Custom environment post processor.
 *
 * @author yujuan
 * @time 2019 /08/28 00:36:08
 */
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final Properties properties = new Properties();
    /**
     * The Profiles.
     */
    private String[] profiles = {
            "custom.properties",
    };

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        for (String profile : profiles) {
            Resource resource = new ClassPathResource(profile);
            environment.getPropertySources().addLast(loadProfiles(resource));
        }
    }

    private PropertySource<?> loadProfiles(Resource resource) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("file" + resource + "not exist");
        }
        try {
            properties.load(resource.getInputStream());
            return new PropertiesPropertySource(resource.getFilename(), properties);
        } catch (IOException ex) {
            throw new IllegalStateException("load resource exception" + resource, ex);
        }
    }
}
```
## 3、在META-INF下创建spring.factories，并且引入CustomEnvironmentPostProcessor 类
```
org.springframework.boot.env.EnvironmentPostProcessor=org.yujuan.springbootlearning.processor.CustomEnvironmentPostProcessor
```
![spring.factories](https://i.loli.net/2019/08/28/VJC4hbeDZqQkXPr.png)


## 4、验证
通过@value 直接引入或者上下文调用，发现已经获取到这个配置参数了
![验证结果](https://i.loli.net/2019/08/28/Wmiv5VnP1HbA74Z.png)

> 欢迎关注博客[这个需求做不了](https://www.cnblogs.com/jakaBlog/)

> 项目地址:[github](https://github.com/yujuangithub0805/springboot-learning.git) EnvironmentPostProcessor 分支