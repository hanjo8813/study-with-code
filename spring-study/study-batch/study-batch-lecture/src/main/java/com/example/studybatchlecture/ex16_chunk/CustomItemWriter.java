package com.example.studybatchlecture.ex16_chunk;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomItemWriter implements ItemWriter<Dummy> {

    @Override
    public void write(List<? extends Dummy> dummies) throws Exception {
        dummies.forEach(dummy -> System.out.println(dummy.getStr()));
    }
}
