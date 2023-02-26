package com.example.practiceredis.repository;

import com.example.practiceredis.entity.Dummy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DummyCrudRepositoryTest {

    @Autowired
    DummyCrudRepository repository;

    @Test
    void 생성_조회_테스트() {
        Dummy dummy = Dummy.builder()
            .key("key")
            .str("str")
            .num(1)
            .lists(List.of("A", "B", "C"))
            .sets(Set.of("A", "B", "C"))
            .maps(Map.of("A", 1, "B", 2))
            .subDummy(Dummy.SubDummy.builder().str("sub-A").num(1).build())
            .build();

        Dummy save = repository.save(dummy);
        String key = save.getKey();
        System.out.println(key);

        Dummy dummy1 = repository.findById(key).get();
        System.out.println(dummy1);
    }

}