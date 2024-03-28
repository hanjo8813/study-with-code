package com.example.practicejpa;

import com.example.practicejpa.entity.Dummy;
import com.example.practicejpa.entity.Dummy1;
import com.example.practicejpa.entity.Dummy2;
import com.example.practicejpa.entity.Dummy3;
import com.example.practicejpa.repository.Dummy1Repository;
import com.example.practicejpa.repository.Dummy2Repository;
import com.example.practicejpa.repository.Dummy3Repository;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
class MultiDummyTest {

    @Autowired
    Dummy1Repository repository1;
    @Autowired
    Dummy2Repository repository2;
    @Autowired
    Dummy3Repository repository3;

    @BeforeAll
    void beforeAll() {

        System.out.println("=============================== before all ===============================");
        Dummy1 dummy1 = repository1.save(
                Dummy1.builder()
                        .str("1")
                        .num(1)
                        .build()
        );

        Dummy2 dummy2 = repository2.save(
                Dummy2.builder()
                        .str("1")
                        .num(1)
                        .dummy1(dummy1)
                        .build()
        );

        Dummy3 dummy3 = repository3.save(
                Dummy3.builder()
                        .str("1")
                        .num(1)
                        .dummy2(dummy2)
                        .build()
        );
    }

    @Test
    void temp() {
        System.out.println("=============================== test ===============================");

        List<Dummy1> all = repository1.findAll();

//        System.out.println(all);
    }
}
