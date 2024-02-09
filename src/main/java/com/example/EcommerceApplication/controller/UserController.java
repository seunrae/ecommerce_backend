package com.example.EcommerceApplication.controller;

import com.example.EcommerceApplication.dto.UserResponse;
import com.example.EcommerceApplication.dto.UserUpdateRequest;
import com.example.EcommerceApplication.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(id, userUpdateRequest);
    }
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return userService.getUsers();
    }

}
