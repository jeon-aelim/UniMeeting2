package com.example.unimeeting.repository;

import com.example.unimeeting.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUserId(String user_id);
}
