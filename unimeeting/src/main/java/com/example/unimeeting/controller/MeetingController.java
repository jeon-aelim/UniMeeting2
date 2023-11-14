package com.example.unimeeting.controller;

import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.dto.MeetingResponse;
import com.example.unimeeting.service.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
     // security를 사용해 user 정보 받기. (user마다 response하는 값이 다름.)

    @GetMapping("/{idx}")
    public ResponseEntity<MeetingResponse> getOne(@PathVariable int id){
        Meeting meeting = meetingService.findById(id);


    }


}
