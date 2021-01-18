package org.example.mapper;

import org.example.model.Stock;
import org.example.model.StockInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StockMapper {

    @Mappings({
            @Mapping(target = "stockInformation", source = "s"),
            @Mapping(target = "id", source = "id")
    })
    Stock getStockDTO(String id, StockInformation s);


}
