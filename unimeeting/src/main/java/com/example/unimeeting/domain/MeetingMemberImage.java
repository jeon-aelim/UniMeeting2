//package com.example.unimeeting.domain;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import java.time.LocalDateTime;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.format.annotation.DateTimeFormat;
//
//@Entity
//@ToString
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name="meeting")
//public class MeetingMemberImage {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private int idx;
//
//  private String title;
//  private String content;
//  private String category;
//  private Integer recruits;
//
//  @Column(name="writer_nickname")
//  private String nickname;
//
//  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH-mm")
//  @Column(name = "start_datetime")
//  private LocalDateTime startDatetime;
//
//  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH-mm")
//  @Column(name = "created_datetime")
//  private LocalDateTime createdDatetime;
//
//  @ManyToOne
//  @JoinColumn(name="member_meeting_idx")
//  private Member member;
//
//  @ManyToOne
//  @JoinColumn(name="meeting_image_meeting_idx")
//  private MeetingImage meetingImage;
//}
