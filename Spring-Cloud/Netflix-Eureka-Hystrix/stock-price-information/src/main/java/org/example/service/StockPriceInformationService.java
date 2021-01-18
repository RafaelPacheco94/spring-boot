package org.example.service;

import org.example.model.StockPriceInformation;
import org.springframework.stereotype.Service;

@Service
public interface StockPriceInformationService {
    public StockPriceInformation getStockPriceInformation(String id);
}
