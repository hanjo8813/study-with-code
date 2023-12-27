package com.example.studybatchlecture.test.arch.chunk;

import java.util.List;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class TargetMemberReader implements ItemStreamReader<Long> {

    @Value("#{jobExecutionContext['memberNos']}")
    private List<Long> memberNos;
    private int nextIndex = 0;

    @Override
    public Long read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("----- read -----");

        Long memberNo = null;
        if (nextIndex < memberNos.size()) {
            memberNo = memberNos.get(nextIndex);
            nextIndex++;
        }
        System.out.println(memberNo);

        //
//        if(nextIndex == 7L) {
//            throw new RuntimeException();
//        }
        //

        return memberNo;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("----- [stream] open -----");

        if (executionContext.containsKey("nextIndex")) {
            nextIndex = executionContext.getInt("nextIndex");
        } else {
            executionContext.put("nextIndex", nextIndex);
        }
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        System.out.println("----- [stream] update -----");
        executionContext.put("nextIndex", nextIndex);
        System.out.println(nextIndex);
        System.out.println("=====================================");
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("----- [stream] close -----");
    }
}
