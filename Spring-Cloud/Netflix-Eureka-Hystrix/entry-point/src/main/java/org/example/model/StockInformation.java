package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInformation {
    private String name;
    private String fullName;
    private String company;
    private StockPriceInformation stockPriceInformation;
    private TraderSentiments traderSentiments;
}
