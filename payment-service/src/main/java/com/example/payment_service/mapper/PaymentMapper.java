package com.example.payment_service.mapper;

import com.example.payment_service.model.Payment;
import org.example.dto.payment.message.PaymentMessageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMessageDto modelToDTto(Payment payment);
    Payment dtoToModel(PaymentMessageDto dto);

}
