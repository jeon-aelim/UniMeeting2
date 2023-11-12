package com.example.unimeeting.controller;

import com.example.unimeeting.domain.Board;
import com.example.unimeeting.service.BoardService;
import com.example.unimeeting.service.CurrentUserProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CurrentUserProvider currentUserProvider;
    @Autowired
    public BoardController(BoardService boardService, CurrentUserProvider currentUserProvider){
        this.boardService=boardService;
        this.currentUserProvider = currentUserProvider;
    }
    @GetMapping("/boards/{type}")
    public List<Board> getAllBoards(@PathVariable String type, @RequestParam(required = false) String search) {
        return boardService.findByType(type, search);
    }
    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable int id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok(board);
    }

    @PostMapping("/boards")
    public ResponseEntity<String> createBoard(@RequestBody Board board) {
        boardService.save(board);
        return ResponseEntity.status(HttpStatus.CREATED).body("Board created successfully");
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable int id, @RequestBody Board board) {
        if (!isAuthorized(id, currentUserProvider.getCurrentUserNickname())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다");
        }
        boardService.update(id, board);
        return ResponseEntity.ok("Board updated successfully");
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable int id) {
        if (!isAuthorized(id, currentUserProvider.getCurrentUserNickname())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다.");
        }
        boardService.deleteById(id);
        return ResponseEntity.ok("Board deleted successfully");
    }

    // Helper 메서드: 게시글 작성자 확인
    private boolean isAuthorized(int boardId, String writerNickname) {
        // 여기에서는 SecurityContext를 통해 현재 사용자의 정보를 가져와 확인합니다.
        String currentUserNickname = currentUserProvider.getCurrentUserNickname();
        return currentUserNickname != null && currentUserNickname.equals(writerNickname);
    }
}
