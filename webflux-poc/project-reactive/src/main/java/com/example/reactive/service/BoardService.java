//package com.example.reactive.service;
//
//import com.example.domain.Board;
//import com.example.reactive.repository.BoardRepository;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RequiredArgsConstructor
//@Service
//public class BoardService {
//
//    private final BoardRepository repository;
//
//    public Flux<Board> findAll() {
//        return repository.findAll();
//    }
//
//    @Transactional
//    public Mono<Board> insertDummy() {
//        return repository.save(
//                Board.builder()
//                        .memberId(1)
//                        .title("title")
//                        .content("content")
//                        .build()
//        );
//    }
//}
