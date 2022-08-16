package com.example.ordermanagementsystem.persistence.repo;

import com.example.ordermanagementsystem.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
