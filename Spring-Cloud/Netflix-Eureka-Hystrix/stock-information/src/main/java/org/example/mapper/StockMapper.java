package org.example.mapper;


import org.example.model.StockInformation;
import org.example.model.StockNamingInformation;
import org.example.model.StockPriceInformation;
import org.example.model.TraderSentiments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mappings({
            @Mapping(target = "stockPriceInformation", source = "s"),
            @Mapping(target = "traderSentiments", source = "t"),
            @Mapping(target = "name", source = "sN.name"),
            @Mapping(target = "fullName", source = "sN.fullName"),
            @Mapping(target = "company", source = "sN.company")
    })
    StockInformation stockInformationMapper(StockPriceInformation s,
                                            TraderSentiments t, StockNamingInformation sN);
}
