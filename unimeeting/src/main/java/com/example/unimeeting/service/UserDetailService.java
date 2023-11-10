package com.example.unimeeting.service;

import com.example.unimeeting.domain.User;
import com.example.unimeeting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String user_id){
        return userRepository.findByUserId(user_id)
                .orElseThrow(() -> new IllegalArgumentException(user_id));
    }
}
