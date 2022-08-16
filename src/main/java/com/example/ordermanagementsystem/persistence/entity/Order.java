package com.example.ordermanagementsystem.persistence.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "date_of_submission")
    private LocalDate dateOfSubmission;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Column(name = "order_line_id")
    private List<OrderLine> orderLines;

    public static Order of(Customer customer, List<OrderLine> orderLines) {
        Order order = new Order();
        order.orderLines = orderLines;
        order.customer = customer;
        order.dateOfSubmission = LocalDate.now();
        return order;
    }

}
