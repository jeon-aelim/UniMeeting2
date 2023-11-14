package com.example.unimeeting.controller;

import com.example.unimeeting.domain.Board;
import com.example.unimeeting.service.BoardService;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
=======
>>>>>>> c950df6 (ignore idea)
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //=============글 목 ===========//
    @GetMapping("/boards/{type}")
    public ResponseEntity<List<Board>> getAllBoards(@PathVariable String type, @RequestParam(required = false) String search) {
        List<Board> board = boardService.findByType(type,search);
        return ResponseEntity.ok(board);
    }


    //=============글 상세 ===========//
    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable int id) {
        Board board = boardService.findById(id);
        return ResponseEntity.ok(board);
    }
    //=============글 쓰기 ===========//
    @PostMapping("/boards")
    public ResponseEntity<String> createBoard(@RequestBody Board board) {
        boardService.save(board);
        return ResponseEntity.status(HttpStatus.CREATED).body("Board created successfully");
    }


    //=============글 수정 ===========//
    @PutMapping("/boards/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable int id, @RequestBody Board board) {

        boardService.update(id, board);
        return ResponseEntity.ok("Board updated successfully");
    }
    //=============글 삭제 ==============//
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable int id) {
        boardService.deleteById(id);
        return ResponseEntity.ok("Board deleted successfully");
    }

}
