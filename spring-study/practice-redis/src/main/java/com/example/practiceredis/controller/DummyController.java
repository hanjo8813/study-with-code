package com.example.practiceredis.controller;

import com.example.practiceredis.entity.Dummy;
import com.example.practiceredis.repository.DummyRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/dummy")
@RestController
public class DummyController {

    private final DummyRedisRepository repository;
    private static int count = 0;

    @GetMapping("/create")
    public void create() {
        Dummy dummy = Dummy.builder()
            .key("key" + count)
            .str("str" + count)
            .num(count)
            .lists(List.of("A", "B", "C"))
            .sets(Set.of("A", "B", "C"))
            .maps(Map.of("A", 1, "B", 2))
            .subDummy(Dummy.SubDummy.builder().str("sub-A").num(1).build())
            .build();
        repository.save(dummy);
        count++;
    }

    @GetMapping("/read/{key}")
    public ResponseEntity<Dummy> read(@PathVariable String key) {
        boolean b = repository.existsById(key);
        System.out.println(b);

        Dummy dummy = repository.findById(key).orElseThrow(() -> new RuntimeException("없어"));
        return ResponseEntity.ok(dummy);
    }
}
