package com.example.unimeeting.controller;

import com.example.unimeeting.dto.MeetingWithDetailsDTO;
import com.example.unimeeting.service.MypageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/mypage")
@CrossOrigin(origins="*")
public class MypageController {

    private final MypageService service;

    @Operation(summary = "모임 리스트를 출력")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "참여 모임 리스트",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = MeetingWithDetailsDTO.class)) })
    })
    @GetMapping("/meetings/participated")
    public ResponseEntity<List<MeetingWithDetailsDTO>> attendList() {
        ResponseEntity<List<MeetingWithDetailsDTO>> entity = new ResponseEntity<>(service.joinMeeting(52), HttpStatus.OK);

        return entity;
    }

    @Operation(summary = "모임 리스트를 출력")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "생성 모임 리스트",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = MeetingWithDetailsDTO.class)) })
    })
    @GetMapping("/meetings/created")
    public ResponseEntity<List<MeetingWithDetailsDTO>> createList() {
        ResponseEntity<List<MeetingWithDetailsDTO>> entity = new ResponseEntity<>(service.createMeeting("aa"), HttpStatus.OK);
        return entity;
    }

    @Operation(summary = "모임 리스트를 출력")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "스크랩 모임 리스트",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = MeetingWithDetailsDTO.class)) })
    })
    @GetMapping("/meetings/scraped")
    public ResponseEntity<List<MeetingWithDetailsDTO>> scrapList() {
        ResponseEntity<List<MeetingWithDetailsDTO>> entity = new ResponseEntity<>(service.scrapMeeting(52), HttpStatus.OK);
        return entity;
    }
}
