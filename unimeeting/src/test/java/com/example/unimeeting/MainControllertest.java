package com.example.unimeeting;

import com.example.unimeeting.controller.MainController;
import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.repository.MeetingRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class MainControllertest {

    @Autowired
    private MeetingRepository repository;
    @BeforeEach()
    void pr() {
        System.out.println("=".repeat(80));
    }


    @Test
    @Order(1) //dh
    public void findAll() {
        List<Meeting> list = repository.findAll();
        list.forEach(System.out::println);
    }

//    @Test
//    @Order(1) //dh
//    public void findAllByOrderByScrab() {
//        List<Meeting> list = repository.findAllByOrderByScrab();
//        list.forEach(System.out::println);
//    }
    @Test
    @Order(2) //dh
    public void findAllByTitleContainingOrContentContaining(){
        List<Meeting> list = repository.findAllByTitleContainingOrContentContaining("","");
        list.forEach(System.out::println);
    }

//    @Test
//    @Order(3) //dh
//    public void {}

    @Test
    @Order(4) //dh
    public void findAllByOrderByTitle(){
        List<Meeting> list = repository.findAllByOrderByTitle();
        list.forEach(System.out::println);
    }
    @Test
    @Order(5) //dh
    public void findAllByOrderByCreatedDatetime() {
        List<Meeting> list = repository.findAllByOrderByCreatedDatetime();
        list.forEach(System.out::println);
    }
}
