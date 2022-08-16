package com.example.ordermanagementsystem.rest.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Accessors(chain = true)
public class CustomerDto {

    @NotNull
    @NotEmpty(message = "registration code must be specified")
    private String registrationCode;

    @NotNull
    @NotEmpty(message = "registration code must be specified")
    private String fullName;

    @NotNull
    @NotEmpty(message = "email code must be specified")
    @Email(message = "email is not in correct format")
    private String email;

    @NotNull
    @NotEmpty(message = "telephone code must be specified")
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    private String phoneNumber;

    private List<OrderLineDto> orders;
}
