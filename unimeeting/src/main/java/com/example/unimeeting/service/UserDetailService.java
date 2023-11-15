package com.example.unimeeting.service;

import com.example.unimeeting.domain.User;
import com.example.unimeeting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public User loadUserByUsername(String user_id) {
        return repository.findByUserId(user_id)
                .orElseThrow(() -> new IllegalArgumentException(user_id));
    }


    /////////////////////////////////////////////////////////////////////////
    public Optional<User> findByUserId(String user_id) {
        return repository.findByUserId(user_id);
    }

    public Optional<User> findByNickname(String nickname) {
        return repository.findByNickname(nickname);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<User> findByUserIdAndPassword(String user_id, String password) {
        return repository.findByUserIdAndPassword(user_id, password);
    }


    public List<User> findByUserIdStartingWithOrNicknameStartingWith(String user_id, String nickname) {
        return repository.findByUserIdStartingWithOrNicknameStartingWith(user_id, nickname);
    }
    //////////////////////////////////////////////////////////////////////////////////

    private void validateDuplicateUserId(User user) {
        repository.findByUserId(user.getUserId())
                .ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        });
    }

    private void validateDuplicatenickname(User user){
        repository.findByNickname(user.getNickname())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 닉네임입니다.");
                });
    }
    private void validateDuplicateemail(User user){
        repository.findByEmail(user.getEmail())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

}

















