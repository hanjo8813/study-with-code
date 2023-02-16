package com.example.mvc.service;

import com.example.domain.Board;
import com.example.mvc.repository.BoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository repository;

    public List<Board> findAll() {
        return repository.findAll();
    }

    public Board insertDummy() {
        return repository.save(
                Board.builder()
                        .memberId(1)
                        .title("title")
                        .content("content")
                        .build()
        );
    }
}
