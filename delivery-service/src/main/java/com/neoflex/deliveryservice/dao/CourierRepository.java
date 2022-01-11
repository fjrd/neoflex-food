package com.neoflex.deliveryservice.dao;

import com.neoflex.deliveryservice.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
}
