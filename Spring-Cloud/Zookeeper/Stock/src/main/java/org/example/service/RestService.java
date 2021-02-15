package org.example.service;

import org.example.model.StockPriceInfo;
import org.example.model.TraderSentiments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    @Autowired
    private RestTemplate restTemplate;

    public StockPriceInfo getStockPriceInfo(int stockID) {
        return restTemplate.getForObject("http://stock-price-info/api/getPriceInfo/" + stockID, StockPriceInfo.class);
    }

    public TraderSentiments getTraderSentiments(int stockID) {
        return restTemplate.getForObject("http://trader-sentiments/api/getTradersSentiments/" + stockID, TraderSentiments.class);
    }

}
