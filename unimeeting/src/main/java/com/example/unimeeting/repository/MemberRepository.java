package com.example.unimeeting.repository;

import com.example.unimeeting.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    public int countByMeetingIdx(int idx);

    public boolean existsMyMeetingIdxAndUserIdx(int meeting_idx, int user_idx);

//    @Query("select m.meeting_idx, m.user_idx, m.accepted m.user.nickname, m.user.category " +
//            "from Member m where m.meeting_idx = :idx")
//            public ApplicantVO findMember

    public boolean deleteByMeetingIdxAndUserIdx(int meeting_idx, int user_idx);


}
