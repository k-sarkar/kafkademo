package com.kafka.demo.config;

import com.kafka.demo.pojo.UserMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.lang.Nullable;



public class DemoProducerListener implements ProducerListener<String, UserMessage> {

    Logger logger = LoggerFactory.getLogger(DemoProducerListener.class.getName());

    public void onSuccess(ProducerRecord<String, UserMessage> producerRecord, RecordMetadata recordMetadata) {
        logger.info("User has been successfully created {}",producerRecord.value().getEmail());
    }

    public void onError(ProducerRecord<String, UserMessage> producerRecord, @Nullable RecordMetadata recordMetadata, Exception exception) {
        logger.info("Failed to created user {}",producerRecord.value().getEmail());
    }
}
