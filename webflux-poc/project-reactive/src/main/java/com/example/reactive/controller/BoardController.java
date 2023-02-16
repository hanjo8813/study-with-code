package com.example.reactive.controller;

import com.example.domain.Board;
import com.example.reactive.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/reactive/boards")
@RestController
public class BoardController {

    private final BoardService service;

    @GetMapping("/findAll")
    public Flux<Board> findAll(){
        return service.findAll();
    }

    @GetMapping("/insertDummy")
    public Mono<Board> insertDummy(){
        return service.insertDummy();
    }
}
