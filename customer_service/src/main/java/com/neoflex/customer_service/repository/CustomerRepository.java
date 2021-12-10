package com.neoflex.customer_service.repository;


import com.neoflex.customer_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> getByPhone(String phone);
}
