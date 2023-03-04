package com.example.mvc.controller;

import com.example.domain.Board;
import com.example.mvc.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/mvc/boards")
@RestController
public class BoardController {

    private final BoardRepository repository;

    @GetMapping("/findAll")
    public ResponseEntity<List<Board>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

//    @GetMapping("/insertDummy")
//    public ResponseEntity<Board> insertDummy() {
//        return repository.save(
//                Board.builder()
//                        .memberId(1)
//                        .title("title")
//                        .content("content")
//                        .build()
//        );
//        return ResponseEntity.ok(service.insertDummy());
//    }
}
