package org.example.service;

import org.example.model.StockPriceInformation;
import org.springframework.stereotype.Service;

@Service
public class StockPriceInformationServiceImpl implements StockPriceInformationService{
    @Override
    public StockPriceInformation getStockPriceInformation(String id) {
        return new StockPriceInformation(1, 2, 1);
    }
}
