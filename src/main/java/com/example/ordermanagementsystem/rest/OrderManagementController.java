package com.example.ordermanagementsystem.rest;
import com.example.ordermanagementsystem.persistence.entity.Order;
import com.example.ordermanagementsystem.rest.dto.*;
import com.example.ordermanagementsystem.services.CustomerService;
import com.example.ordermanagementsystem.services.OrderService;
import com.example.ordermanagementsystem.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/order/management")
public class OrderManagementController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final ProductService productService;

    private final CustomerDtoMapper customerDtoMapper;
    private final ProductDtoMapper productDtoMapper;


    @PostMapping("product")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto product) {
        productService.save(productDtoMapper.map(product));
        return ResponseEntity.ok().build();
    }

    @PostMapping("customer")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDto customer) {
        customerService.save(customerDtoMapper.map(customer));
        return ResponseEntity.ok().build();
    }

    @PostMapping("orders")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDto order) {
        orderService.save(order.getCustomerId(), order.getOrderLines());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("orders/{orderId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long orderId, @Valid @RequestBody OrderLineDto orderLine) {
        orderService.updateOrderLine(orderId, orderLine);
        return ResponseEntity.ok().build();
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getOrdersByParam(@RequestParam(required = false) Long prodId,
                                                        @RequestParam(required = false) Long customerId,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(orderService.findByParam(prodId, customerId, date));
    }
}
