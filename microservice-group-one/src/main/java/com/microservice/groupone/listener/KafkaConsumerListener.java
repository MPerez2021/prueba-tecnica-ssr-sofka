package com.microservice.groupone.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class KafkaConsumerListener {

    @KafkaListener(topics = {"transaction-topic"}, groupId = "group_id")
    public void listener(String message){
        log.info("Message received: {}", message);
    }
}
