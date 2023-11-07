package com.example.unimeeting.repository;

import com.example.unimeeting.domain.MeetingMemberImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingMemberImageRepository extends JpaRepository<MeetingMemberImageRepository, Integer> {
  public List<MeetingMemberImage> findByNickname(String nickname);
}
