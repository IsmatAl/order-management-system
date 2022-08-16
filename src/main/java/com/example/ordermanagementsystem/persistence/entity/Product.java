package com.example.ordermanagementsystem.persistence.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Accessors(chain = true)
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sku_code")
    private String skuCode;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

}
