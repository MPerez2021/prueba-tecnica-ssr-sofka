package com.microservice.group_two.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){
        Map<String, String> configuration = new HashMap<>();
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

        return TopicBuilder.name("transaction-topic")
                .partitions(1)
                .replicas(1)
                .configs(configuration)
                .build();
    }
}
