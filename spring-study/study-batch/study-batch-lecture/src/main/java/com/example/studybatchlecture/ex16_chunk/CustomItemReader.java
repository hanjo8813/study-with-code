package com.example.studybatchlecture.ex16_chunk;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomItemReader implements ItemReader<Dummy> {

    private final List<Dummy> list = new ArrayList<>(Arrays.asList(
            new Dummy("a"),
            new Dummy("b"),
            new Dummy("c")
    ));

    @Override
    public Dummy read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!list.isEmpty()){
            return list.remove(0);
        }
        return null;
    }
}
