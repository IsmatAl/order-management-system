package com.example.ordermanagementsystem.services;

import com.example.ordermanagementsystem.persistence.entity.Customer;
import com.example.ordermanagementsystem.persistence.entity.Order;
import com.example.ordermanagementsystem.persistence.entity.OrderLine;
import com.example.ordermanagementsystem.persistence.entity.Product;
import com.example.ordermanagementsystem.persistence.repo.CustomerRepo;
import com.example.ordermanagementsystem.persistence.repo.OrderRepo;
import com.example.ordermanagementsystem.persistence.repo.ProductRepo;
import com.example.ordermanagementsystem.rest.dto.OrderLineDto;
import com.example.ordermanagementsystem.rest.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    public void save(Long customerId, List<OrderLineDto> orderLineDtoList) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (customer == null) {
            throw new EntityNotFoundException(Customer.class, "id", customerId.toString());
        }
        List<OrderLine> orderLines = orderLineDtoList.stream().map(lineDto -> {
            Long productId = lineDto.getProductId();
            Product product = productRepo.findById(productId).orElse(null);
            if (product == null) {
                throw new EntityNotFoundException(Product.class, "id", productId.toString());
            }
            OrderLine orderLine = OrderLine.of(product, lineDto.getQuantity());
            return orderLine;
        }).collect(Collectors.toList());
        Order order = Order.of(customer, orderLines);
        orderRepo.save(order);
    }

    public List<Order> findByParam(Long prodId, Long customerId, LocalDate localDateTime) {
        if (prodId != null)
            return findByProduct(prodId);
        if (customerId != null)
            return findByCustomer(customerId);
        else return findByDate(localDateTime);
    }

    private List<Order> findByProduct(Long prodId) {
        Product product = productRepo.findById(prodId).orElse(null);
        if (product == null) {
            throw new EntityNotFoundException(Product.class, "id", prodId.toString());
        }
        List<Order> orders = orderRepo.findByProduct(prodId);
        return orders;
    }

    private List<Order> findByDate(LocalDate date) {
        List<Order> orders = orderRepo.findByDate(date);
        return orders;
    }

    private List<Order> findByCustomer(Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElse(null);
        if (customer == null) {
            throw new EntityNotFoundException(Customer.class, "id", customerId.toString());
        }
        List<Order> orders = orderRepo.findByCustomer(customerId);
        return orders;
    }

    public void updateOrderLine(Long orderId, OrderLineDto orderLine) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order == null) {
            throw new EntityNotFoundException(Order.class, "id", orderId.toString());
        }
        Long lineId = orderLine.getId();
        OrderLine line = getOrderLine(lineId, order);
        if (line == null) {
            throw new EntityNotFoundException(Order.class, "id", lineId.toString());
        }

        order.getOrderLines().remove(line);
        OrderLine newLine = OrderLine.of(line.getProduct(), orderLine.getQuantity());
        order.getOrderLines().add(newLine);
        orderRepo.save(order);
    }

    private OrderLine getOrderLine(Long lineId, Order order) {
        OrderLine line = order
                .getOrderLines()
                .stream()
                .filter((OrderLine x) -> x.getId().equals(lineId))
                .findFirst().orElse(null);
        if (line == null) return null;
        return line;
    }
}
