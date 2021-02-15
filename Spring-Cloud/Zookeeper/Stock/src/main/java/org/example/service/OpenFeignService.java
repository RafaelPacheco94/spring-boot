package org.example.service;

import org.example.client.StockPriceInfoAPI;
import org.example.client.TraderSentimentsAPI;
import org.example.model.StockPriceInfo;
import org.example.model.TraderSentiments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenFeignService {

    @Autowired
    private TraderSentimentsAPI traderSentimentsAPI;

    @Autowired
    private StockPriceInfoAPI stockPriceInfoAPI;

    public TraderSentiments getTraderSentiments(int stockID) {
        return traderSentimentsAPI.getStockPriceInfo(stockID);
    }

    public StockPriceInfo getStockPriceInfo(int stockID){
        return stockPriceInfoAPI.getStockPriceInfo(stockID);
    }

}
