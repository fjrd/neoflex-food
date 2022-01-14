package com.neoflex.deliveryservice.mapper;

import com.neoflex.deliveryservice.dao.CourierRepository;
import com.neoflex.deliveryservice.model.DeliveryOrder;
import com.neoflex.deliveryservice.service.DeliveryService;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring", uses = { CourierRepository.class })
@Mapper(componentModel = "spring"
//        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface DeliveryMessageMapper {
    DeliveryMessageMapper INSTANCE = Mappers.getMapper(DeliveryMessageMapper.class);

    @Mapping(source = "courier.courierId", target = "assignedCourierId")
    DeliveryMessageDto toDto(DeliveryOrder deliveryOrder);

    //@IterableMapping(qualifiedByName = "getById")
    DeliveryOrder toEntity(DeliveryMessageDto messageDto);

}
