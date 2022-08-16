package com.example.ordermanagementsystem.persistence.repo;

import com.example.ordermanagementsystem.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>, CustomOrderRepo {
}

