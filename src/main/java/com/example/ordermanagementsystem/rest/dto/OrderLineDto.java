package com.example.ordermanagementsystem.rest.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Accessors(chain = true)
public class OrderLineDto {

    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private Long id;

    @NotNull
    @Min(value = 1, message = "Product id is missing")
    private Long productId;

    @NotNull
    @Min(value = 1, message = "Quantity must be bigger than 0")
    private Integer quantity;
}
