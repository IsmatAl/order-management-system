package com.example.ordermanagementsystem.rest.dto;

import com.example.ordermanagementsystem.persistence.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {
    Customer map(CustomerDto customer);
}
