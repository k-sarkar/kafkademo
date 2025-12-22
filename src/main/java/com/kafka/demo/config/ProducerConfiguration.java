package com.kafka.demo.config;

import com.kafka.demo.constant.DemoConstant;
import com.kafka.demo.pojo.UserMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {

    @Bean
    public Map producerConfigs() {
        Map props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DemoConstant.BOOTSTRAP_SERVERS_CONFIG);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // See https://kafka.apache.org/41/documentation/#producerconfigs for more properties
        return props;
    }

    @Bean
    public ProducerFactory<String, UserMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<String, UserMessage>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, UserMessage> kafkaTemplate() {
        KafkaTemplate<String, UserMessage> template =
         new KafkaTemplate<String, UserMessage>(producerFactory());
        template.setProducerListener(new DemoProducerListener());

        return template;
    }
}
