package org.example.client;

import org.example.model.TraderSentiments;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "trader-sentiments")
public interface TraderSentimentsAPI {

    @RequestMapping(value = "/api/getTradersSentiments/{stockID}", method = RequestMethod.GET)
    @ResponseBody
    TraderSentiments getStockPriceInfo(@PathVariable("stockID") int stockID);
}
