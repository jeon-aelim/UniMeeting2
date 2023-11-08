package com.example.unimeeting.repository;

import com.example.unimeeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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
}
