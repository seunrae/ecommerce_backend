package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.dto.*;
import com.example.EcommerceApplication.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> registerUser(UserRegisterRequest userRegisterRequest);
    ResponseEntity<?> verifyUser(UserLoginRequest userLoginRequest) throws UserNotFoundException;
    ResponseEntity<?> updateUser(Long id, UserUpdateRequest userUpdateRequest);
    ResponseEntity<String> deleteUser(Long id);
    ResponseEntity<?> getUserById(Long id);
    ResponseEntity<List<UserResponse>> getUsers();


}
