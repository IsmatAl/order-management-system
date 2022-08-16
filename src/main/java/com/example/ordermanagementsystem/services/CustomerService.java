package com.example.ordermanagementsystem.services;

import com.example.ordermanagementsystem.persistence.entity.Customer;
import com.example.ordermanagementsystem.persistence.repo.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {


    private CustomerRepo customerRepo;

    public void save(Customer customer) {
        customerRepo.save(customer);
    }
}
