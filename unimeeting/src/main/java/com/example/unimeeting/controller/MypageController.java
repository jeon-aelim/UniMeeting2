package com.example.unimeeting.controller;

import com.example.unimeeting.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/mypage")
public class MypageController {
//  @Autowired
    private final MypageService service;



}
