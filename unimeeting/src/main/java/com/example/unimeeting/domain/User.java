package com.example.unimeeting.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private String user_id;
    private String password;
    private String email;
    private String category;
    private String phone_number;
    private String nickname;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards;
}
