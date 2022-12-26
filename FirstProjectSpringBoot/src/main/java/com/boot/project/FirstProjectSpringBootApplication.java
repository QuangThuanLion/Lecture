package com.boot.project;

import com.boot.project.config.AppConfig;
import com.boot.project.config.FirstBean;
import com.boot.project.config.SecondBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class FirstProjectSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstProjectSpringBootApplication.class, args);

       AnnotationConfigApplicationContext applicationContext =
               new AnnotationConfigApplicationContext(AppConfig.class);

       SecondBean secondBean = (SecondBean) applicationContext.getBean("secondBean");
    }
}
