package com.example.mvc.repository;

import com.example.domain.Board;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);

    public List<Board> findAll() {
        return jdbcTemplate.query("select * from board", rowMapper);
    }

    public Board save(Board board) {
        int update = jdbcTemplate.update(
                "insert into board(member_id, title, content) value (:member_id, :title, :content)",
                new HashMap<>() {{
                    put("member_id", board.getMemberId());
                    put("title", board.getTitle());
                    put("content", board.getContent());
                }}
        );
        if (update != 1) {
            throw new RuntimeException("insert 실패");
        }
        return board;
    }
}
