package org.example.controller;

import org.example.mapper.StockMapper;
import org.example.model.StockInformation;
import org.example.service.StockNamingService;
import org.example.service.StockPriceInformationService;
import org.example.service.TraderSentimentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StockInformationController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockNamingService stockNamingService;

    @Autowired
    private StockPriceInformationService stockPriceInformationService;

    @Autowired
    private TraderSentimentsService traderSentimentsService;

    @RequestMapping(value = "/getStockInformation/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StockInformation getStockInfo(@PathVariable String id) {
        return stockMapper.stockInformationMapper(stockPriceInformationService.getStockPriceInformation(id), traderSentimentsService.getTraderSentiments(id),
                stockNamingService.getStockNaming(id));
    }


}


