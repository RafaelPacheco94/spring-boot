package org.example.controller;

import org.example.model.TraderSentiments;
import org.example.service.TraderSentimentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradersSentimentsController {

    @Autowired
    private TraderSentimentsService traderSentimentsService;

    @RequestMapping(value = "/getTraderSentiments/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TraderSentiments getStockInfo(@PathVariable String id) {
        return traderSentimentsService.getTraderSentiments(id);
    }
}
