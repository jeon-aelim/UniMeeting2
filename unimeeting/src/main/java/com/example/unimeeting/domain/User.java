package com.example.unimeeting.domain;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String category;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String role;

    @Builder
    public User(Integer idx,String userId, String password, String nickname, String email, String category, String phoneNumber,String role) {
        this.idx=idx;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.role=role;
    }

    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    ///////////////
    public static User createUser(String userId, String password, PasswordEncoder passwordEncoder, String nickname, String email, String category, String phoneNumber,String role) {
        return new User(null, userId, passwordEncoder.encode(password), nickname,email,category,phoneNumber,role);
    }
    @Data
    public static class LoginRequestDto {
        private String username;
        private String password;
    }
}
