package com.example.my;

import com.example.client.Dummy;
import com.example.client.DummyHttpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyService {

    private final DummyHttpInterface dummyHttpInterface;

    public Dummy getDummy() {
        // 여기서 virtual thread 사용하면 될듯
        return dummyHttpInterface.getDummy(1, 1, "body");
    }
}
