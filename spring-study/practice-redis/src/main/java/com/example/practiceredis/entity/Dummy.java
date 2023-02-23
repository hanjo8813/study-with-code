package com.example.practiceredis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Builder
@RedisHash(value = "keyspace")
public class Dummy {

    @Id
    private String key;
    private String str;
    private int num;

    private List<String> lists;
    private Set<String> sets;
    private Map<String, Integer> maps;

    private SubDummy subDummy;

    @Builder
    public static class SubDummy {
        private String str;
        private int num;
    }
}
