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
