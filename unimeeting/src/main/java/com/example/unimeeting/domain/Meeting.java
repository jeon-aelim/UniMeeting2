package com.example.unimeeting.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="meeting")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String title;
    private String content;
    private String category;
    private Integer recruits;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH-mm")
    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH-mm")
    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @ManyToOne
    @JoinColumn(name = "writer_nickname", referencedColumnName = "nickname")
    private User user;

    @Builder
    public Meeting(String title, String content, String category, Integer recruits, LocalDateTime startDatetime, LocalDateTime createdDatetime){
        this.title = title;
        this.content = content;
        this.category = category;
        this.recruits = recruits;
        this.startDatetime = startDatetime;
        this.createdDatetime = createdDatetime;
    }

}
