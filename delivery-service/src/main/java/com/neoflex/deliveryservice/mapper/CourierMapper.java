package com.neoflex.deliveryservice.mapper;

import com.neoflex.deliveryservice.model.Courier;
import com.neoflex.deliveryservice.model.to.CourierDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourierMapper {
    CourierDto toDto(Courier courier);

    Courier toEntity(CourierDto courierDto);
}
