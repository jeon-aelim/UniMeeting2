package com.example.unimeeting.repository;

import com.example.unimeeting.domain.MeetingMemberImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeetingMemberImageRepository extends JpaRepository<MeetingMemberImage, Integer> {
  public List<MeetingMemberImage> findByNickname(String nickname);
  public int countByNickname(String nickname);

  @Query("SELECT m FROM Meeting m " +
      "JOIN (SELECT meetingIdx, COUNT(*) AS nowRecruits " +
      "FROM MeetingMember GROUP BY meetingIdx) mb " +
      "ON m.idx = mb.meetingIdx " +
      "LEFT JOIN (SELECT meetingIdx, imageUrl AS imgUrl " +
      "FROM MeetingImage GROUP BY meetingIdx) mi " +
      "ON m.idx = mi.meetingIdx " +
      "WHERE m.idx IN (SELECT meetingIdx FROM MeetingMember " +
      "WHERE writer_nickname = :nickname)")
  List<MeetingMemberImage> findAttendListByUsernickname(@Param("nickname") String nickname);
}
