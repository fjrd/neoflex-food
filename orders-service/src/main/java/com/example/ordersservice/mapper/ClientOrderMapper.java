package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import dto.ClientOrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientOrderMapper {
    ClientOrderDto modelToDto(Order order);
    Order dtoToModel(ClientOrderDto dto);
}
