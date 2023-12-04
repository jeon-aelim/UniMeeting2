package com.example.unimeeting.dto;

import com.example.unimeeting.domain.Board;
import com.example.unimeeting.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResponse {
    private Integer idx;

    private String title;
    private String content;
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH-mm")
    private LocalDateTime createdDatetime;
    private User user;

    private boolean isWriter;

    public BoardResponse(Board board , boolean isWriter){
        this.idx=board.getIdx();
        this.content=board.getContent();
        this.createdDatetime=board.getCreatedDatetime();
        this.title=board.getTitle();
        this.type=board.getType();
        this.user=board.getUser();
        this.isWriter=isWriter;

    }

}
