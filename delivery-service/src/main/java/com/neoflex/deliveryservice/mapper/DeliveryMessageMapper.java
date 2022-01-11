package com.neoflex.deliveryservice.mapper;

import com.neoflex.deliveryservice.model.DeliveryOrder;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryMessageMapper {
    DeliveryMessageMapper INSTANCE = Mappers.getMapper(DeliveryMessageMapper.class);

    @Mapping(source = "courier.courierId", target = "assignedCourierId")
    DeliveryMessageDto toDto(DeliveryOrder deliveryOrder);

    @Mapping(source = "assignedCourierId", target = "courier.courierId")
    DeliveryOrder toEntity(DeliveryMessageDto messageDto);

}
