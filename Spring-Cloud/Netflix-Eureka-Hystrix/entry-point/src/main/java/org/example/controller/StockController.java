package org.example.controller;

import org.example.mapper.StockMapper;
import org.example.model.Stock;
import org.example.model.StockInformation;
import org.example.service.StockInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class StockController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockInformationService stockInformationService;

    @RequestMapping(value = "/getStockInfo/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Stock getStockInfo(@PathVariable String id) {
        return stockMapper.getStockDTO(id, stockInformationService.getStockInformation(id));
    }
}
