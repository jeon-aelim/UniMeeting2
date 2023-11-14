package com.example.unimeeting.repository;

import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.dto.MeetingWithDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    // DB에 있는 meeting row의 category들을 중복 제외하고 List로 반환.
    // meeting page에 사용
    @Query("select distinct m.category from Meeting m order by m.category")
    public List<String> findDistinctCategoryBy();

    // 모든 meeting글에서 검색
    public List<Meeting> findAllByTitleContainingOrContentContaining(String title,String content);

//     해당 category 내의 meeting글에서 검색
    @Query("select m from Meeting m  where m.category=:category and (title like %:title% or content like %:content%)")
    public List<Meeting> searchMeetingInCategory(@Param("category") String category, @Param("title") String title, @Param("content") String content);

    // idx의 글의 작성자가 user_nickname인지 확인
    // meeting 글 상세 보기 페이지에서 버튼
    public boolean existsByIdxAndUserNickname(int idx, String user_nickname);

    // 참여한 소모임 리스트
    @Query("select m from Meeting m where m.idx in (select mb.meetingIdx from Member mb where mb.user.idx = :idx)")
    public List<Meeting> searchMeetingInMemberIDX(int idx);

    // 생성한 소모임 리스트
    public List<Meeting> findByUserNickname(String nickname);

    // 스크랩한 소모임 리스트
    @Query("select m from Meeting m where m.idx in (select s.meetingIdx from Scrap s where s.user.idx = :idx)")
    public List<Meeting> searchMeetingInScrapIDX(int idx);
}
