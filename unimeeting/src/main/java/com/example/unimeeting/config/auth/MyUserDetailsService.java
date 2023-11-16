package com.example.unimeeting.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.unimeeting.repository.UserRepository;
import com.example.unimeeting.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService : in UserDetailsService");
        UserDetails user = new MyUserDetails(userRepository.findByUserId(user_id).get());

        return user;
    }
}
