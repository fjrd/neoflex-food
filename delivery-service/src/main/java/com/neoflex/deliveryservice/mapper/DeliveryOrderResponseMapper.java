package com.neoflex.deliveryservice.mapper;


import com.neoflex.deliveryservice.model.DeliveryOrder;
import com.neoflex.deliveryservice.model.to.DeliveryOrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryOrderResponseMapper {

    DeliveryOrderResponseMapper FILTERED_MAPPER = Mappers.getMapper(DeliveryOrderResponseMapper.class);

    DeliveryOrderResponseDto toDto(DeliveryOrder deliveryOrder);

    List<DeliveryOrderResponseDto> toListOfDto(List<DeliveryOrder> applications);
    DeliveryOrder toEntity(DeliveryOrderResponseDto responseDto);

}
