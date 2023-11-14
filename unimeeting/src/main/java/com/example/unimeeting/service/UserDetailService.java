package com.example.unimeeting.service;

import com.example.unimeeting.domain.User;
import com.example.unimeeting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailService(UserRepository repository){
        this.repository=repository;
    }
    @Override
    public User loadUserByUsername(String user_id){
        return repository.findByUserId(user_id)
                .orElseThrow(() -> new IllegalArgumentException(user_id));
    }
    ////////////////////////////////////////
    public Optional<User> findByUserId(String user_id){
        return repository.findByUserId(user_id);
    }


}
