package com.neoflex.customer_service.service;

import com.neoflex.customer_service.exception.NotValidException;
import com.neoflex.customer_service.mapper.CustomerMapper;
import com.neoflex.customer_service.model.Customer;
import com.neoflex.customer_service.model.to.CustomerDto;
import com.neoflex.customer_service.model.to.CustomerEditDto;
import com.neoflex.customer_service.repository.CustomerRepository;
import com.neoflex.customer_service.util.JwtTokenProvider;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final PasswordEncoder bCryptPasswordEncoder;

//    private final JwtTokenProvider tokenProvider;

    @Autowired
    public CustomerService(CustomerRepository repository,
                           CustomerMapper mapper,
                           PasswordEncoder bCryptPasswordEncoder
//                           JwtTokenProvider tokenProvider
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.tokenProvider = tokenProvider;
    }

    public CustomerDto get(String phone, String password) {
        Customer customer = repository
                .getByPhone(phone)
                .orElseThrow(() -> new NotValidException("Phone not valid "));
        System.out.println(customer.getPassword());
        validatePassword(password, customer.getPassword());
        return mapper.entityToDto(customer);
    }

    public CustomerDto create(@NonNull CustomerEditDto editDto) {
        Customer newCustomer = mapper.dtoToEntity(editDto);
        newCustomer.setPassword(bCryptPasswordEncoder.encode(editDto.getPassword()));
        Customer savedCustomer;
        try {
            savedCustomer = repository.save(newCustomer);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Phone already exist");
        }
        return mapper.entityToDto(repository.save(savedCustomer));
    }

    private void validatePassword(String password, String hash) {
        if (!bCryptPasswordEncoder.matches(password, hash)) {
            throw new NotValidException("Password not valid");
        }
    }
}
