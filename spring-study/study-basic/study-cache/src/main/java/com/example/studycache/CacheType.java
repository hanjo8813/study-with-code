package com.example.studycache;

import lombok.Getter;

@Getter
public enum CacheType {

    DUMMY("dummy",5 * 60, 10000),
    DUMMY2("dummy2",5 * 60, 10000);

    CacheType(String cacheName, int expireAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = maximumSize;
    }

    private final String cacheName;
    private final int expireAfterWrite;
    private final int maximumSize;
}
