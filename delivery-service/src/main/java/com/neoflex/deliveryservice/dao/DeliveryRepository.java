package com.neoflex.deliveryservice.dao;

import com.neoflex.deliveryservice.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<DeliveryOrder, UUID> {
}
