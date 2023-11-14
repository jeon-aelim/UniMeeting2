package com.example.unimeeting.service;

import com.example.unimeeting.repository.MeetingRepository;
import com.example.unimeeting.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MainService {
    private final MeetingRepository meetingRepository;
    private final ScrapRepository scrapRepository;


}
