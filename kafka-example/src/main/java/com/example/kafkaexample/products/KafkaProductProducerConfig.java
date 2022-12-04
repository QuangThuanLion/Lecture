package com.example.kafkaexample.products;

import com.example.kafkaexample.ProductRequest;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProductProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

//    public Map<String, Object> producerConfig(){
//        Map<String, Object> properties = new HashMap<>();
//
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//        return properties;
//    }
//
//    @Bean(name = "producerFactoryProduct")
//    public ProducerFactory<String, ProductRequest> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfig());
//    }
//
//    @Bean(name = "kafkaTemplateProduct")
//    public KafkaTemplate<String, ProductRequest> kafkaTemplate(ProducerFactory<String, ProductRequest> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }

    @Bean(name = "producerFactoryProduct")
    public ProducerFactory<String, ProductRequest> producerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
        configProps.putAll(kafkaProperties.getProperties());
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "kafkaTemplateProduct")
    public KafkaTemplate<String, ProductRequest> kafkaTemplate(KafkaProperties kafkaProperties) {
        return new KafkaTemplate<>(producerFactory(kafkaProperties));
    }


    // record count
//    @Bean
//    public ProducerFactory<String, String> producerRecordCountFactory(KafkaProperties kafkaProperties) {
//        Map<String, Object> configProps = new HashMap<>();
//
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
//        configProps.putAll(kafkaProperties.getProperties());
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }

//    @Bean
//    public KafkaTemplate<String, String> recoudCountkafkaTemplate(KafkaProperties kafkaProperties) {
//        return new KafkaTemplate<>(producerRecordCountFactory(kafkaProperties));
//    }

    @Bean(name = "jobConvKafkaTemplateProduct")
    public KafkaTemplate<String, ProductRequest> jobConvKafkaTemplate(KafkaProperties kafkaProperties) {
        return new KafkaTemplate<>(producerJobConvFactory(kafkaProperties));
    }

    @Bean(name = "producerJobConvFactoryProduct")
    public ProducerFactory<String, ProductRequest> producerJobConvFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "20971520");
        configProps.putAll(kafkaProperties.getProperties());
        return new DefaultKafkaProducerFactory<>(configProps);
    }
}
