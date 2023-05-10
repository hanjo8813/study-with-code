package com.example.studycache;

import org.springframework.stereotype.Repository;

@Repository
public class DummyRepository {

    private static final Dummy dummy = Dummy.builder().num(1).str("A").build();

    public Dummy getDummy() {
        System.out.println("call repository");
        return dummy;
    }
}
