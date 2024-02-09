package com.example.EcommerceApplication.dto;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserLoginRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank(message = "password should not be blank")
    private String password;
}
