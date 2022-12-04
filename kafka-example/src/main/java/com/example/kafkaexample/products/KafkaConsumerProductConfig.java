package com.example.kafkaexample.products;

import com.example.kafkaexample.ProductRequest;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerProductConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

//    public Map<String, Object> consumerConfig() {
//        Map<String, Object> properties = new HashMap<>();
//
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        return properties;
//    }
//
//    @Bean(name = "ConsumerFactoryProduct")
//    public ConsumerFactory<String, ProductRequest> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new JsonDeserializer<>(ProductRequest.class));
//        //return new DefaultKafkaConsumerFactory<>(consumerConfig());
//    }
//
//    @Bean(name = "KafkaListenerContainerFactoryProduct")
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ProductRequest>> factory(
//            ConsumerFactory<String, ProductRequest> consumerFactory
//    ) {
//        ConcurrentKafkaListenerContainerFactory<String, ProductRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//
//        return factory;
//    }

    public ConsumerFactory<String, ProductRequest> consumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.kafkaexample");

        configProps.putAll(kafkaProperties.getProperties());
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(ProductRequest.class));
    }

    @Bean(name = "csvParmJobKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, ProductRequest> csvParmJobKafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, ProductRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }

    public ConsumerFactory<String, ProductRequest> consumerJobConvFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);

        configProps.putAll(kafkaProperties.getProperties());
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(ProductRequest.class));
    }

    @Bean(name = "jobConvKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, ProductRequest> jobConvKafkaListenerContainerFactory(KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<String, ProductRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerJobConvFactory(kafkaProperties));
        return factory;
    }
}
