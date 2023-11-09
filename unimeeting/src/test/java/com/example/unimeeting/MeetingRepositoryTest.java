package com.example.unimeeting;

import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.domain.User;
import com.example.unimeeting.repository.MeetingRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Test
    @Order(5)
    @Transactional
    @Rollback(value = false)
    public void save() {
        Meeting meeting = Meeting.builder()
                .title("test2")
                .content("test2")
                .category("운동")
                .recruits(3)
                .location("서울")
                .startDatetime(LocalDateTime.now())
                .build();

        User user = User.builder()
                .userId("aelim")
                .password("1234")
                .email("devaelim@gmail.com")
                .nickname("aa")
                .phoneNumber("01092708011")
                .category("코딩")
                .build();
        user.setIdx(123);
        meeting.setCreatedDatetime(LocalDateTime.now());
        meeting.setUser(user);
        System.out.println(user);
        System.out.println(meeting);
        repository.save(meeting);
        System.out.println(meeting.getIdx());
    }

    @Test
    @Order(6)
    public void findById(){
        System.out.println(repository.findById(87));
    }

    @Test
    @Order(7)
    public void deleteById(){
        System.out.println(repository.findById(87));
        repository.deleteById(87);
        System.out.println(repository.findById(87));
    }

    @Test
    @Order(8)
    @Transactional
    @Rollback(value = false)
    public void updateMeeting(){
        Meeting oldMeeting = repository.findById(87).get();

        oldMeeting.setContent("updateTest");

        System.out.println(repository.findById(87));
    }
}
