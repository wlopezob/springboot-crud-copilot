package com.wlopezob.personav1.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("springdoc.info")
@Getter
@Setter
public class OpenApiProperties {
    private String version;
    private String title;
    private String description;
    private List<String> servers;
}
