package com.example.unimeeting.repository;

import com.example.unimeeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
<<<<<<< HEAD
public interface MeetingRepository extends JpaRepository<Meeting, Integer>{

    public List<Meeting> findAll(); //전체

    public List<Meeting> findAllByTitleContainingOrContentContaining(String title,String content); //검색

//    public List<Meeting> 인기순
    public List<Meeting> findAllByOrderByTitle(); //제목
    public List<Meeting> findAllByOrderByCreatedDatetime(); //시간



    /*@Select("select * from meeting left join meeting_image on meeting.idx = meeting_image.meeting_idx group by meeting.idx") //전체
    public List<InfoDTO> listM();

    @Select("select * from meeting where content_text like concat('%',#{key},'%') or title like concat('%',#{key},'%')") //검색
    public List<InfoDTO> searchM1(String keyword);

    @Select("select * from meeting as a left join\n" + "(select meeting_idx, image_url from meeting_image group by meeting_idx) as b on a.idx = b.meeting_idx left join\n" +
            "(select meeting_idx, count(*) as scrap_cnt from scrap group by meeting_idx) as c on b.meeting_idx = c.meeting_idx order by scrap_cnt desc;") // 인기순

    public List<InfoDTO> popularSort();

    @Select("select * from meeting left join meeting_image on meeting.idx = meeting_image.meeting_idx group by meeting.idx order by binary(title)") // 제목순
    public List<InfoDTO> titleSort();

    @Select("select * from meeting left join meeting_image on meeting.idx = meeting_image.meeting_idx group by meeting.idx order by binary(created_datetime) desc ") //최신순
    public List<InfoDTO> createDate();*/
=======

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

>>>>>>> main
}
