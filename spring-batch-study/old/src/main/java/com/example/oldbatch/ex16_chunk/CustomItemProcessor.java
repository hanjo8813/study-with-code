package com.example.oldbatch.ex16_chunk;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomItemProcessor implements ItemProcessor<Dummy, Dummy> {

    @Override
    public Dummy process(Dummy dummy) throws Exception {
        dummy.setStr(dummy.getStr().toUpperCase());
        return dummy;
    }
}
