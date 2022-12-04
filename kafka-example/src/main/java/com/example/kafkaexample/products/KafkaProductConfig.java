package com.example.kafkaexample.products;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProductConfig {

    @Bean(name = "topicConfigurationProduct")
    public NewTopic topicConfiguration(){
        return TopicBuilder.name("PRODUCT_TOPIC")
                .build();
    }
}
