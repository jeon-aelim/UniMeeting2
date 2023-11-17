package com.example.unimeeting.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@ToString
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private boolean accepted;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;


    @Column(name = "meeting_idx")
    private Integer meetingIdx;

//    @ManyToOne
//    @JoinColumn(name = "meeting_idx")
//    private Meeting meeting;
    @Builder
    public Member(Integer meetingIdx, User user) {
        this.meetingIdx = meetingIdx;
        this.user = user;
        this.user = user;
    }

    public void update() {
        accepted = !accepted;
    }

}
