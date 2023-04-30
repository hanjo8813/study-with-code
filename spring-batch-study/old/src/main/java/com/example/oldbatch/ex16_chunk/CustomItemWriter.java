package com.example.oldbatch.ex16_chunk;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class CustomItemWriter implements ItemWriter<Dummy> {

    @Override
    public void write(List<? extends Dummy> dummies) throws Exception {
        dummies.forEach(dummy -> System.out.println(dummy.getStr()));
    }
}
