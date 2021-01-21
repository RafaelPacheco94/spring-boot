package org.example.task;

import org.example.model.Stock;
import org.example.model.StockList;
import org.example.model.StockPriceInfo;
import org.example.model.TraderSentiments;
import org.example.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Producer {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Scheduled(fixedRate = 4000L)
    public void sendStockListKafkaProducer() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(new Date(), "1", "Fac", "FACE", new StockPriceInfo(24.3, 245.1, 1), new TraderSentiments(23, 77)));
        stockList.add(new Stock(new Date(), "1", "Fac", "123", new StockPriceInfo(24.3, 245.1, 1), new TraderSentiments(23, 77)));
        stockList.add(new Stock(new Date(), "1", "Fac", "312312", new StockPriceInfo(24.3, 245.1, 1), new TraderSentiments(23, 77)));

        kafkaProducerService.sendStockList(new StockList(stockList));

    }

    @Scheduled(fixedRate = 5000L)
    public void sendStockKafkaProducer() {
        kafkaProducerService.sendStock(new Stock(new Date(), "1", "Fac", "FACE", new StockPriceInfo(24.3, 245.1, 1), new TraderSentiments(23, 77)));
    }

    @Scheduled(fixedRate = 6000L)
    public void sendSimpleMessageKafkaProducer() {
        kafkaProducerService.sendSimpleMessage("Simple Message!");
    }
}