package com.example.EcommerceApplication.dto;

import com.example.EcommerceApplication.model.ROLE;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserUpdateRequest {
    @NotBlank(message = "name should not be blank")
    private String name;

    @NotBlank(message = "address should not be blank")
    private String address;

    @NotBlank(message = "phone number should not be blank")
    @Pattern(regexp = "^\\d{11}")
    private String phone;

    @Email
    @NotBlank
    private String email;
}
