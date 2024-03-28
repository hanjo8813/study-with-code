package com.example.studybatchlecture.test.arch.tasklet;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class ExtractTargetMemberTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("----- ExtractTargetMemberTasklet -----");

        boolean isStored = contribution.getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .containsKey("memberNos");
        System.out.println(isStored);

        // 이것 저것 조회
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(100000000L + i);
        }

//        long[] primitiveArray = list.stream()
//                .mapToLong(i -> i)
//                .toArray();
//        System.out.println(primitiveArray.getClass().getName());

        Long[] refArray = list.toArray(new Long[0]);
        System.out.println(refArray.getClass().getName());


        // => context 문자열이 일정 크기를 넘으면 SERIALIZABLE_CONTEXT 컬럼으로 저장되지만, 이것도 용량 제한이 있음.

        contribution.getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("memberNos", refArray);

//        throw new RuntimeException();
        return RepeatStatus.FINISHED;
    }
}
