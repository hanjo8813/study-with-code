package com.example.studybatchlecture.test.arch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExtractTargetMemberTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 이것 저것 조회하기?
        Queue<Long> memberNos = new LinkedList<>(List.of(1L, 2L, 3L, 4L, 5L));
        contribution.getStepExecution().getJobExecution().getExecutionContext().put("memberNos", memberNos);
        return RepeatStatus.FINISHED;
    }
}
