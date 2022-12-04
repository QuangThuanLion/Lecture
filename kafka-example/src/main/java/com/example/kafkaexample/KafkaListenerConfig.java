package com.example.kafkaexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerConfig {

    @KafkaListener(
            topics = "USERPROFILE",
            groupId = "groupId"
    )
    public void listenerConfig(String data){
        System.out.println("Listener received: " +data);
    }

    @KafkaListener(
            topics = "PRODUCT_TOPIC",
            groupId = "groupId",
            containerFactory = "csvParmJobKafkaListenerContainerFactory"
    )
    public void listenerConfigProduct(ProductRequest product) {
        System.out.println("Listener kafka: " + product);
    }

    @KafkaListener(
            topics = "PRODUCT_TOPIC_NAME",
            groupId = "groupId"
    )
    public void listenerConfigProductByName(String product) throws JsonProcessingException {
        System.out.println("Listener from product by name: " + product);
//        ObjectMapper objectMapper = new ObjectMapper()
//                .setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

        ObjectMapper objectMapper = new ObjectMapper();

        Gson gson = new Gson();
//        String productString = gson.toJson(product);
        ProductResponse productResponse = gson.fromJson(product.toLowerCase(), ProductResponse.class);
        System.out.println("productResponse: " + productResponse.toString());

        ProductResponse productResponse1 = objectMapper.readValue(product, ProductResponse.class);
        System.out.println("productRequest: " + productResponse1.toString());
    }

//    public static void main(String[] args) throws JsonProcessingException {
////        String product = "{\"ID\":\"20\",\"NAME\":\"HONDA\",\"PRICE\":5000.0,\"DESCRIPTION\":\"TAUROUS DESCRIPTION 2\",\"STATUS\":false}";
//          ObjectMapper mapper = new ObjectMapper();
////        ProductResponse productResponse = mapper.readValue(product, ProductResponse.class);
////        System.out.println(productResponse.toString());
//
//        String json = "{\"ID\":\"20\",\"NAME\":\"HONDA\",\"PRICE\":5000.0,\"DESCRIPTION\":\"TAUROUS DESCRIPTION 2\",\"STATUS\":false}";
//        ProductResponse car = mapper.readValue(json.toLowerCase(), ProductResponse.class);
//        System.out.println(car);
//    }
}
