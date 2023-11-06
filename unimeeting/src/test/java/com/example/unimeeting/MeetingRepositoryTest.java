package com.example.unimeeting;

import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.repository.MeetingRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MeetingRepositoryTest {
    @Autowired
    MeetingRepository repository;
    @BeforeEach()
    void pr() {
        System.out.println("=".repeat(80));
    }

    @Test
    public void findDistinctCategory(){
        List<String> list =repository.findDistinctCategoryBy();
        list.forEach(System.out::println);
    }

    @Test
    public void findByTitleContainsOrContentContains(){
        List<Meeting> list = repository.findByTitleContainsOrContentContains("", "");
        list.forEach(System.out::println);
    }

}
