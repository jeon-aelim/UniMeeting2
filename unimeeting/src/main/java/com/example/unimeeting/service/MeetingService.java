package com.example.unimeeting.service;


import com.example.unimeeting.domain.Meeting;
import com.example.unimeeting.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MeetingService {

    @Autowired
    MeetingRepository repository;

    public Meeting findById(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }
}
