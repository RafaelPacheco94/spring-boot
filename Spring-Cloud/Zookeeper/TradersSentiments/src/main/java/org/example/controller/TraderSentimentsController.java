package org.example.controller;

import org.example.model.TraderSentiments;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RefreshScope
public class TraderSentimentsController {

    @Value("${buyers}")
    private int buyers;

    @Value("${sellers}")
    private int sellers;

    @RequestMapping(value = "/getTradersSentiments/{stockID}", method = RequestMethod.GET)
    public TraderSentiments getStockPriceInfo(@PathVariable int stockID) {
        return new TraderSentiments(buyers, sellers);
    }

}
