package com.example.kafkaexample;

import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListerConfigStream {

    @KafkaListener(
            topics = "USERPROFILE_TOPIC",
            groupId = "groupId"
    )
    public void listenerConfig(Object data){
        System.out.println("Listener config new data stream: " + data);
    }

    @KafkaListener(
            topics = "USERPROFILE_TABLE",
            groupId = "groupId"
    )
    public void listenerConfigUserProfile(String data) {
        System.out.println("Listener config userprofile_table: " + data);
    }

    @KafkaListener(
            topics = "KSQL_USERPROFILE_STREAMMING_DATA",
            groupId = "groupId"
    )
    public void listenerConfigUserProfileUpdate(List<String> data){
        data.forEach(x -> {
            System.out.println("Listener config userprofile update: " + x);
        });
    }
}
