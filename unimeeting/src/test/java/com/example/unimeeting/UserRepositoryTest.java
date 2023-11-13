package com.example.unimeeting;

import com.example.unimeeting.domain.User;
import com.example.unimeeting.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userR;

    @Test
    void showall(){
        List<User> list = userR.findAll();
        list.stream().forEach(System.out::println);
    }
}
