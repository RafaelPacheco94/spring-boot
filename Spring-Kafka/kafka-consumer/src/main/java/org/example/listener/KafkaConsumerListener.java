package org.example.listener;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.model.Stock;
import org.example.model.StockList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    private final Logger logger
            = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @Value("${consumer.kafka.stock.topic-name-stock-list}")
    private String stockListTopic;

    @Value("${consumer.kafka.stock.topic-name-stock}")
    private String stockTopic;

    @Value(value = "${consumer.kafka.stock.topic-name-simple-message}")
    private String simpleMessageTopic;

    @KafkaListener(topics = "${consumer.kafka.stock.topic-name-stock-list}",
            groupId = "${consumer.kafka.stock.group-name}",
            containerFactory = "stockListKafkaListenerContainerFactory")
    public void listenSenderStockList(ConsumerRecord<Integer, StockList> data) {
        logger.info("[{}] Received message with value: {} from topic: {} with value size: {} and key size: {}", data.timestamp(), data.value(), data.topic(), data.serializedValueSize(), data.serializedKeySize());
    }

    @KafkaListener(topics = "${consumer.kafka.stock.topic-name-stock}",
            groupId = "${consumer.kafka.stock.group-name}",
            containerFactory = "stockKafkaListenerContainerFactory")
    public void listenSenderStock(ConsumerRecord<Integer, Stock> data) {
        logger.info("[{}] Received message with value: {} from topic: {} with value size: {} and key size: {}", data.timestamp(), data.value(), data.topic(), data.serializedValueSize(), data.serializedKeySize());
    }

    @KafkaListener(topics = "${consumer.kafka.stock.topic-name-simple-message}",
            groupId = "${consumer.kafka.stock.group-name}",
            containerFactory = "simpleMessageKafkaListenerContainerFactory")
    public void listenSenderSimpleMessage(ConsumerRecord<Integer, String> data, @Header(KafkaHeaders.GROUP_ID) String g) {
        logger.info("[{}] Received message with value: {} from topic: {} with value size: {} and key size: {}", data.timestamp(), data.value(), data.topic(), data.serializedValueSize(), data.serializedKeySize());
    }

}
