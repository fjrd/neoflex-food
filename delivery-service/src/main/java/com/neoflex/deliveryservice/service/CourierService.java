package com.neoflex.deliveryservice.service;

import com.neoflex.deliveryservice.dao.CourierRepository;
import com.neoflex.deliveryservice.mapper.CourierMapper;
import com.neoflex.deliveryservice.model.Courier;
import com.neoflex.deliveryservice.model.to.CourierDto;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class CourierService {
    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;

    public CourierService(CourierRepository courierRepository, CourierMapper courierMapper) {
        this.courierRepository = courierRepository;
        this.courierMapper = courierMapper;
    }

    public void create(@NotNull CourierDto courierDto) {
        courierRepository.save(courierMapper.toEntity(courierDto));
    }
}
