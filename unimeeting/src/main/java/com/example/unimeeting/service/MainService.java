package com.example.unimeeting.service;

import com.example.unimeeting.domain.Board;
import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.repository.MeetingRepository;
import com.example.unimeeting.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MainService {
    private final MeetingRepository meetingRepository;
    private final ScrapRepository scrapRepository;

//    public Meeting findAll() {
//        return meetingRepository.findAll()
//                .orElseThrow(() -> new IllegalArgumentException("not found : "));
//    }


}
