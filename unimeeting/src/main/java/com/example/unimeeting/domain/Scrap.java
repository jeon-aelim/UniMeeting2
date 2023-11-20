package com.example.unimeeting.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scrap")
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "user_idx")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "meeting_idx")
//    private Meeting meeting;
    @Column(name = "meeting_idx")
    @NonNull
    private Integer meetingIdx;
}
