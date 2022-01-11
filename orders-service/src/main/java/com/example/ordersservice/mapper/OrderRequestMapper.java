package com.example.ordersservice.mapper;

import com.example.ordersservice.model.DishOrder;
import com.example.ordersservice.model.Order;
import org.example.dto.order.request.OrderRequestDto;
import org.example.dto.restaurant.request.DishRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {


    Order dtoToModel(OrderRequestDto dto);

    @Mapping(source = "dishId", target = "dish.dishId")
    @Mapping(source = "", target = "order")
    List<DishOrder> dishOrderRequestsToDishOrder(List<DishRequestDto> dishRequestDtoList);


//    @Mapping(
//            target = "orderId",
//            expression = "java(new TimeAndFormat( s.getTime(), s.getFormat() ))"
//    )

}
