package com.example.ordermanagementsystem.services;

import com.example.ordermanagementsystem.persistence.entity.Product;
import com.example.ordermanagementsystem.persistence.repo.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public void save(Product product) {
        productRepo.save(product);
    }

}
