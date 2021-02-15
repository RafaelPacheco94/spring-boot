package org.example.controller;

import org.example.model.Stock;
import org.example.model.StockPriceInfo;
import org.example.model.TraderSentiments;
import org.example.service.OpenFeignService;
import org.example.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping(value = "/rest")
@RefreshScope
public class RestTemplateController {

    @Autowired
    private RestService restService;

    @Value("${stock.id}")
    private String stock_ID;

    @Value("${stock.company}")
    private String stock_company;

    @Value("${stock.abbreviation}")
    private String stock_abbreviation;

    @RequestMapping(value = "/getStock/{stockID}", method = RequestMethod.GET)
    public Stock getStock(@PathVariable int stockID) {
        return new Stock(new Date(), stock_ID, stock_company, stock_abbreviation,
                restService.getStockPriceInfo(stockID),
                restService.getTraderSentiments(stockID));
    }

}