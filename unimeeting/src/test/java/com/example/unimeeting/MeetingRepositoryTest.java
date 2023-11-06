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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MeetingRepositoryTest {
    @Autowired
    MeetingRepository repository;
    @BeforeEach()
    void pr() {
        System.out.println("=".repeat(80));
    }

    @Test
    @Order(1)
    public void findDistinctCategory(){
        List<String> list =repository.findDistinctCategoryBy();
        list.forEach(System.out::println);
    }

    @Test
    @Order(2)
    public void findByTitleContainsOrContentContains(){
        List<Meeting> list = repository.findAllByTitleContainingOrContentContaining("", "");
        list.forEach(System.out::println);
    }

    @Test
    @Order(3)
    public void searchMeetingInCategory(){
        List<Meeting> list = repository.searchMeetingInCategory("운동", "", "");
        list.forEach(System.out::println);
    }

    @Test
    @Order(4)
    public void existsByIdxAndUserNickname() {
        System.out.println(repository.existsByIdxAndUserNickname(55, "aa"));
    }

}
