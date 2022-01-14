package com.example.ordersservice.mapper;

import com.example.ordersservice.model.Order;
import org.example.dto.order.OrderMessageDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = DishMessageMapper.class, builder = @Builder(disableBuilder = true))
public interface OrderMessageMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "assignedCourier.courierId", target = "assignedCourierId")
    OrderMessageDto modelToDto(Order order);

    @Mapping(source = "dishesList", target = "dishesList", ignore = true)
    @Mapping(source = "assignedCourierId", target = "assignedCourier.courierId")
    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    void updateFromDtoToModel(OrderMessageDto dto, @MappingTarget Order order);

    @AfterMapping
    default void checkThatCourierIdIsNotNull(@MappingTarget Order order){
        if (order.getAssignedCourier().getCourierId() == null)
            order.setAssignedCourier(null);
    }

}
