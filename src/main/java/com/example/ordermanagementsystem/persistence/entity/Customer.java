package com.example.ordermanagementsystem.persistence.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_code")
    private String registrationCode;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany
    @Column(name = "order_id")
    private List<Order> orders;

}
