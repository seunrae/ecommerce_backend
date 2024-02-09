package com.example.EcommerceApplication.service.impl;

import com.example.EcommerceApplication.repository.UserRepository;
import com.example.EcommerceApplication.service.UserDetailService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
