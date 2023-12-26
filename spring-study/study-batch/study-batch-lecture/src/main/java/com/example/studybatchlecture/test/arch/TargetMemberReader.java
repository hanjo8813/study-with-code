package com.example.studybatchlecture.test.arch;


import java.util.List;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

//@Component
public class TargetMemberReader implements ItemStreamReader<Integer> {

//    private final List<Long> memberNos;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("----- stream open -----");

        List<Integer> memberNos = (List<Integer>) executionContext.get("memberNos");



    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void close() throws ItemStreamException {

    }
}
