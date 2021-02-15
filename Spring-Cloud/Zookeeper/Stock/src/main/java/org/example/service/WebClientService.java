package org.example.service;

import org.example.model.StockPriceInfo;
import org.example.model.TraderSentiments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public StockPriceInfo getStockPriceInfo(int stockID) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://stock-price-info/api/getPriceInfo/" + stockID)
                .retrieve()
                .bodyToMono(StockPriceInfo.class)
                .block();
    }

    public TraderSentiments getTraderSentiments(int stockID) {
        return webClientBuilder
                .build()
                .get()
                .uri("http://trader-sentiments/api/getTradersSentiments/" + stockID)
                .retrieve()
                .bodyToMono(TraderSentiments.class)
                .block();
    }


}
