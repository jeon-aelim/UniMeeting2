package com.example.unimeeting.service;

import com.example.unimeeting.dto.MeetingMemberImageDTO;
import com.example.unimeeting.repository.MeetingImageRepository;
import com.example.unimeeting.repository.MeetingRepository;
import com.example.unimeeting.repository.MemberRepository;
import com.example.unimeeting.repository.ScrapRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
//@Service
//public class MypageService {
//
//  private final MeetingRepository meetingR;
//
//  public List<MeetingMemberImageDTO> findByCreatedMeeting(int idx) {
//    return meetingR.findByCreatedMeeting(idx);
//  }
//
//}
