package org.example.controller;

import org.example.model.StockPriceInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RefreshScope
public class StockPriceInfoController {

    @Value("${buy}")
    private int buy;

    @Value("${sell}")
    private int sell;

    @Value("${alteration}")
    private int alteration;

    @RequestMapping(value = "/getPriceInfo/{stockID}", method = RequestMethod.GET)
    public StockPriceInfo getStockPriceInfo(@PathVariable int stockID) {
        return new StockPriceInfo(buy, sell, alteration);
    }

}
