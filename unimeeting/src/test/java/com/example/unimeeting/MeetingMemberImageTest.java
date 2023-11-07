package com.example.unimeeting;

import com.example.unimeeting.domain.MeetingMemberImage;
import com.example.unimeeting.repository.MeetingMemberImageRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MeetingMemberImageTest {
  @Autowired
  MeetingMemberImageRepository mr;

//  @Test
//  void showAll(){
//    List<MeetingMemberImage> list = mr.findAll();
//    list.stream().forEach(System.out::println);
//  }
  static String nickname = "aa";
  @Test
  void count(){
    int cnt = mr.countByNickname(nickname);
    System.out.println(cnt);
  }
  @Test
  void show(){
    List<MeetingMemberImage> list = mr.findByNickname(nickname);
    list.stream().forEach(System.out::println);
  }
}
