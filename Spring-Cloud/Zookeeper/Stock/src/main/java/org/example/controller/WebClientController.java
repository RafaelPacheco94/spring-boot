package org.example.controller;

import org.example.model.Stock;
import org.example.model.StockPriceInfo;
import org.example.model.TraderSentiments;
import org.example.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@RestController
@RequestMapping(value = "/webclient")
@RefreshScope
public class WebClientController {

    @Autowired
    private WebClientService webClientService;

    @Value("${stock.id}")
    private String stock_ID;

    @Value("${stock.company}")
    private String stock_company;

    @Value("${stock.abbreviation}")
    private String stock_abbreviation;

    @RequestMapping(value = "/getStock/{stockID}", method = RequestMethod.GET)
    public Stock getStock(@PathVariable int stockID) {
        return new Stock(new Date(), stock_ID, stock_company, stock_abbreviation,
                webClientService.getStockPriceInfo(stockID),
                webClientService.getTraderSentiments(stockID));
    }

}
