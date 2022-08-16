package com.example.ordermanagementsystem.rest.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductDto {

    @NotEmpty(message = "Name not specified")
    private String name;

    @NotEmpty(message = "Name not specified")
    private String skuCode;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    @NotNull(message = "price not specified")
    private BigDecimal unitPrice;
}
