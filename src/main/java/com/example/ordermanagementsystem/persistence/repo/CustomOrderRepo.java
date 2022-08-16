package com.example.ordermanagementsystem.persistence.repo;

import com.example.ordermanagementsystem.persistence.entity.Order;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CustomOrderRepo {
    @Query("select o from Order o join o.orderLines ol where ol.id in (select ol.id from OrderLine ol where ol.product.id = ?1)")
    List<Order> findByProduct(Long prodId);

    @Query("select o from Order o where o.dateOfSubmission = ?1")
    List<Order> findByDate(LocalDate dateOfSubmission);

    @Query("select o from Order o where o.customer.id = ?1")
    List<Order> findByCustomer(Long customerId);
}
