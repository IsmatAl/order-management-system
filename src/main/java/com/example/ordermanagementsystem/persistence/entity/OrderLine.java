package com.example.ordermanagementsystem.persistence.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public static OrderLine of(Product product, int quantity) {
        OrderLine orderLine = new OrderLine();
        orderLine.quantity = quantity;
        orderLine.product = product;
        return orderLine;
    }
}
