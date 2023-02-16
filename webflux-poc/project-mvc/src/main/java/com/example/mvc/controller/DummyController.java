package com.example.mvc.controller;

import com.example.domain.Board;
import com.example.mvc.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/dummy")
@RestController
public class DummyController {

    private final BoardRepository boardRepository;

    @GetMapping
    public void createDummy() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 1; j <= 5; j++) {
                boardRepository.save(
                        Board.builder()
                                .memberId(j)
                                .title("title" + i)
                                .content("content" + i)
                                .build()
                );
            }
        }
    }
}
