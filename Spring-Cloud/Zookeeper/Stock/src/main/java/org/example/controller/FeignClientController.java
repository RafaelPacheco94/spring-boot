package org.example.controller;


import org.example.model.Stock;
import org.example.service.OpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/feign")
@RefreshScope
public class FeignClientController {

    @Autowired
    private OpenFeignService openFeignService;

    @Value("${stock.id}")
    private String stock_ID;

    @Value("${stock.company}")
    private String stock_company;

    @Value("${stock.abbreviation}")
    private String stock_abbreviation;

    @RequestMapping(value = "/getStock/{stockID}", method = RequestMethod.GET)
    public Stock getStock(@PathVariable int stockID) {
        return new Stock(new Date(), stock_ID, stock_company, stock_abbreviation,
                openFeignService.getStockPriceInfo(stockID),
                openFeignService.getTraderSentiments(stockID));
    }

}
