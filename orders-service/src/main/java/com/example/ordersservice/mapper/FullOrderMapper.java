package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import dto.FullOrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FullOrderMapper {
    FullOrderDto modelToDto(Order order);
    Order dtoToModel(FullOrderDto dto);

}
