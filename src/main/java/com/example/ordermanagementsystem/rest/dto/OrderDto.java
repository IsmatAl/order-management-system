package com.example.ordermanagementsystem.rest.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderDto {

    @NotNull(message = "customer id missing")
    Long customerId;

    @NotNull(message = "orderLines missing")
    @NotEmpty(message = "order lines cannot be empty")
    List<OrderLineDto> orderLines;

}
