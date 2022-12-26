package com.boot.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Bean(name = "firstBean")
    public FirstBean getFirstBean() {
        return new FirstBean();
    }

    @Bean(name = "secondBean")
    @Lazy
    public SecondBean getSecondBean(){
        return new SecondBean();
    }
}
