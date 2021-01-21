package org.example.service;

import org.example.model.Stock;
import org.example.model.StockList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService {

    private final Logger logger
            = LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${consumer.kafka.stock.topic-name-stock-list}")
    private String stockListTopic;

    @Value("${consumer.kafka.stock.topic-name-stock}")
    private String stockTopic;

    @Value(value = "${consumer.kafka.stock.topic-name-simple-message}")
    private String simpleMessageTopic;

    private final KafkaTemplate<Integer, StockList> stockListKafkaTemplate;

    private final KafkaTemplate<Integer, Stock> stockKafkaTemplate;

    private final KafkaTemplate<Integer, String> simpleMessageKafkaTemplate;

    public KafkaProducerService(KafkaTemplate<Integer, StockList> stockListKafkaTemplate,
                                KafkaTemplate<Integer, Stock> stockKafkaTemplate,
                                KafkaTemplate<Integer, String> simpleMessageKafkaTemplate) {
        this.stockListKafkaTemplate = stockListKafkaTemplate;
        this.stockKafkaTemplate = stockKafkaTemplate;
        this.simpleMessageKafkaTemplate = simpleMessageKafkaTemplate;
    }

    public void sendStockList(StockList stockList) {
        ListenableFuture<SendResult<Integer, StockList>> send = stockListKafkaTemplate.send(stockListTopic, stockList);
        send.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Error sending: ", throwable);
            }

            @Override
            public void onSuccess(SendResult<Integer, StockList> integerStockListSendResult) {
                logger.info("Success sending value with size: {} with topic: {} on partition {}", integerStockListSendResult.getRecordMetadata().serializedValueSize(), integerStockListSendResult.getRecordMetadata().topic(), integerStockListSendResult.getRecordMetadata().partition());
            }
        });
    }

    public void sendStock(Stock stock) {
        ListenableFuture<SendResult<Integer, Stock>> send = stockKafkaTemplate.send(stockTopic, stock);
        send.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Error sending: ", throwable);
            }

            @Override
            public void onSuccess(SendResult<Integer, Stock> integerStockListSendResult) {
                logger.info("Success sending value with size: {} with topic: {} on partition {}", integerStockListSendResult.getRecordMetadata().serializedValueSize(), integerStockListSendResult.getRecordMetadata().topic(), integerStockListSendResult.getRecordMetadata().partition());
            }
        });
    }

    public void sendSimpleMessage(String s) {
        ListenableFuture<SendResult<Integer, String>> send = simpleMessageKafkaTemplate.send(simpleMessageTopic, s);
        send.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Error sending: ", throwable);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> integerStockListSendResult) {
                logger.info("Success sending value with size: {} with topic: {} on partition {}", integerStockListSendResult.getRecordMetadata().serializedValueSize(), integerStockListSendResult.getRecordMetadata().topic(), integerStockListSendResult.getRecordMetadata().partition());
            }
        });
    }

}


