package com.example.EcommerceApplication.service.impl;

import com.example.EcommerceApplication.dto.*;
import com.example.EcommerceApplication.exception.UserNotFoundException;
import com.example.EcommerceApplication.model.Product;
import com.example.EcommerceApplication.model.ROLE;
import com.example.EcommerceApplication.model.User;
import com.example.EcommerceApplication.repository.UserRepository;
import com.example.EcommerceApplication.service.JwtService;
import com.example.EcommerceApplication.service.UserDetailService;
import com.example.EcommerceApplication.service.UserMethods;
import com.example.EcommerceApplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public ResponseEntity<?> registerUser(UserRegisterRequest userRegisterRequest) {
        User user =  User.builder()
                .name(userRegisterRequest.getName())
                .address(userRegisterRequest.getAddress())
                .phone(userRegisterRequest.getPhone())
                .email(userRegisterRequest.getEmail())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .role(ROLE.valueOf(userRegisterRequest.getRole().toUpperCase()))
                .build();
        if (user != null) {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Could not register user", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> verifyUser(UserLoginRequest userLoginRequest) throws UserNotFoundException {
        UserMethods userMethods = new UserMethodsImpl();

            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(), userLoginRequest.getPassword()));
                User user = userRepository.findByEmail(userLoginRequest.getEmail()).orElseThrow( () ->new UserNotFoundException(userLoginRequest.getEmail() + "not found"));
                var jwt = jwtService.generateToken(user);

                UserLoginResponse userLoginResponse = new UserLoginResponse();

                userLoginResponse.setUserId(user.getUserId());
                userLoginResponse.setName(user.getName());
                userLoginResponse.setEmail(user.getEmail());
                userLoginResponse.setAddress(user.getAddress());
                userLoginResponse.setPhone(user.getPhone());
                userLoginResponse.setRole(user.getRole());
                userLoginResponse.setCreatedAt(user.getCreatedAt());
                userLoginResponse.setUpdatedAt(user.getUpdatedAt());
                userLoginResponse.setProductList(userMethods.productListToProductUserResponse(user.getProducts()));
                userLoginResponse.setToken(jwt);

                return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
            } catch (Exception ex) {
                return new ResponseEntity<>("Error Invalid Credentials", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        UserMethods userMethods = new UserMethodsImpl();
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user with id "+id+" not found"));
            user.setName(userUpdateRequest.getName());
            user.setAddress(userUpdateRequest.getAddress());
            user.setPhone(userUpdateRequest.getPhone());
            user.setEmail(userUpdateRequest.getEmail());
            userRepository.save(user);
            return new ResponseEntity<>(userMethods.userToUserResponse(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user with id "+id+" not found"));
            userRepository.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUserById(Long id) {
        UserMethods userMethods = new UserMethodsImpl();
        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("user with id "+id+" not found"));
            return new ResponseEntity<>(userMethods.userToUserResponse(user), HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserResponse>> getUsers() {
        UserMethods userMethods = new UserMethodsImpl();
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(userMethods.userListToUserResponse(users), HttpStatus.OK);
    }
}
