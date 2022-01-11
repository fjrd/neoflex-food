package com.neoflex.deliveryservice.service;

import com.neoflex.deliveryservice.dao.CourierRepository;
import com.neoflex.deliveryservice.dao.DeliveryRepository;
import com.neoflex.deliveryservice.mapper.DeliveryMessageMapper;
import com.neoflex.deliveryservice.mapper.DeliveryOrderResponseMapper;
import com.neoflex.deliveryservice.model.Courier;
import com.neoflex.deliveryservice.model.DeliveryOrder;
import com.neoflex.deliveryservice.model.to.DeliveryOrderRequestDto;
import com.neoflex.deliveryservice.model.to.DeliveryOrderResponseDto;
import lombok.NonNull;
import org.example.dto.delivery.DeliveryStatus;
import org.example.dto.delivery.message.DeliveryMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMessageMapper deliveryMessageMapper;
    private final DeliveryOrderResponseMapper responseMapper;
    private final CourierRepository courierRepository;
    private final ProducerService producerService;

    public DeliveryService(DeliveryRepository deliveryRepository,
                           DeliveryMessageMapper deliveryMessageMapper,
                           DeliveryOrderResponseMapper responseMapper,
                           CourierRepository courierRepository,
                           ProducerService producerService
    ) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMessageMapper = deliveryMessageMapper;
        this.responseMapper = responseMapper;
        this.courierRepository = courierRepository;
        this.producerService = producerService;
    }

    public void create(DeliveryMessageDto messageDto) {
        DeliveryOrder order = deliveryMessageMapper.toEntity(messageDto);
        deliveryRepository.save(order);
    }

    public List<DeliveryOrderResponseDto> getAll() {
        return responseMapper.toListOfDto(
                deliveryRepository.findAll()
                        .stream()
                        .filter(this::isNotInJobAndNotSuccessAndNotRejectOrder)
                        .collect(Collectors.toList())
        );
    }

    public DeliveryOrderResponseDto get(UUID uuid) {
        DeliveryOrder order = deliveryRepository.findById(uuid).orElseThrow();
        return responseMapper.toDto(order);
    }

    public void update(@NonNull DeliveryOrderRequestDto requestDto, UUID orderUuid) {
        Courier courier = courierRepository.findById(requestDto.getCourierId()).orElseThrow();
        DeliveryOrder order = deliveryRepository.findById(orderUuid).orElseThrow();
        order.setCourier(courier);
        order.setDeliveryStatus(DeliveryStatus.valueOf(requestDto.getDeliveryStatus()));
        deliveryRepository.save(order);
        producerService.produce(deliveryMessageMapper.toDto(order));
    }

    private boolean isNotInJobAndNotSuccessAndNotRejectOrder(DeliveryOrder order){
        DeliveryStatus status = order.getDeliveryStatus();
        return !status.equals(DeliveryStatus.SUCCESS)
                && !status.equals(DeliveryStatus.CANCELED)
                && !status.equals(DeliveryStatus.REJECTED)
                && !status.equals(DeliveryStatus.UNPROCESSED)
                && !status.equals(DeliveryStatus.IN_PROGRESS);
    }
}
