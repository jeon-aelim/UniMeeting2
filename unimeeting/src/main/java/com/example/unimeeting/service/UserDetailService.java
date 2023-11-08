package com.example.unimeeting.service;

import com.example.unimeeting.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserDetailService extends UserDetailsService {

    private final UserRepository userRepository;
}
