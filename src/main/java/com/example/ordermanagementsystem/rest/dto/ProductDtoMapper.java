package com.example.ordermanagementsystem.rest.dto;

import com.example.ordermanagementsystem.persistence.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {
    Product map(ProductDto product);
}
