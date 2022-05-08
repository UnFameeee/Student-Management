package com.example.springbootcloud.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class AwsConfig {
    private String apiKey = "" ;
    private String secretKey = "";
    private String sessionToken = "";
}
