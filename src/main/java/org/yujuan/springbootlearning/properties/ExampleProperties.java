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
