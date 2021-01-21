package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private Date date;
    private String id;
    private String company;
    private String stockAbbreviation;
    private StockPriceInfo stockPriceInfo;
    private TraderSentiments traderSentiments;

}
