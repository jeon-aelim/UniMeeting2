package com.example.unimeeting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name="meeting")
public class MeetingMJ {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String user_id;
  private String nickname;
  private String email;
  private String category;
  private String phone_number;

  @ManyToOne
  @JoinColumn(name="MEMBER_MEETING_ID")
  private Member member;

  @ManyToOne
  @JoinColumn(name="MEETINGIMAGE_MEETING_ID")
  private MeetingImage meetingImage;
}
