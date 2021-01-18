package org.example.controller;

import org.example.model.StockPriceInformation;
import org.example.service.StockPriceInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockPriceInformationController {

    @Autowired
    private StockPriceInformationService stockPriceInformationService;

    @RequestMapping(value = "/getStockPrice/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StockPriceInformation getStockInfo(@PathVariable String id) {
        return stockPriceInformationService.getStockPriceInformation(id);
    }

}
