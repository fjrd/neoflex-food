package com.neoflex.customer_service.mapper;

import com.neoflex.customer_service.model.Customer;
import com.neoflex.customer_service.model.to.CustomerDto;
import com.neoflex.customer_service.model.to.CustomerEditDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto entityToDto(Customer customer);
    CustomerEditDto entityToEditDto(Customer customer);

    Customer dtoToEntity(CustomerDto dto);
    Customer dtoToEntity(CustomerEditDto editDto);
}
