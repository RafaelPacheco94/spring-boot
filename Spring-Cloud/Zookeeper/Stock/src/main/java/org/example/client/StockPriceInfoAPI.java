package org.example.client;

import org.example.model.StockPriceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "stock-price-info")
public interface StockPriceInfoAPI {

    @RequestMapping(value = "/api/getPriceInfo/{stockID}", method = RequestMethod.GET)
    @ResponseBody
    StockPriceInfo getStockPriceInfo(@PathVariable("stockID") int stockID);

}
