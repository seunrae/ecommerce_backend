package com.example.EcommerceApplication.controller;

import com.example.EcommerceApplication.dto.UserLoginRequest;
import com.example.EcommerceApplication.dto.UserLoginResponse;
import com.example.EcommerceApplication.dto.UserRegisterRequest;
import com.example.EcommerceApplication.dto.UserResponse;
import com.example.EcommerceApplication.exception.UserNotFoundException;
import com.example.EcommerceApplication.service.UserService;
import com.sun.net.httpserver.Authenticator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserAuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        return userService.registerUser(userRegisterRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyUser(@RequestBody @Valid UserLoginRequest userLoginRequest) throws UserNotFoundException {
        return userService.verifyUser(userLoginRequest);
    }
}
