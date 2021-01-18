package org.example.service;

import org.example.model.StockNamingInformation;
import org.springframework.stereotype.Service;

@Service
public class StockNamingService {

    public StockNamingInformation getStockNaming(String id){
        return new StockNamingInformation("ETHUSD","Etherum","Crypto");
    }


}
