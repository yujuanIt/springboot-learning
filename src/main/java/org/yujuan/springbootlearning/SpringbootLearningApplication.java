package org.yujuan.springbootlearning;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringbootLearningApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootLearningApplication.class, args);
    }
}
