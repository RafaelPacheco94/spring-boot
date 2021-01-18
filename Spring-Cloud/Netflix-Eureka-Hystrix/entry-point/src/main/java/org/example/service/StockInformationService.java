package org.example.service;

import org.example.model.StockInformation;
import org.springframework.stereotype.Service;

@Service
public interface StockInformationService {

    public StockInformation getStockInformation(String id);

}
