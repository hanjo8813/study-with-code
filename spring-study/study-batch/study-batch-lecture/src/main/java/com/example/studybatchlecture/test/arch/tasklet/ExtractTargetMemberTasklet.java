package com.example.studybatchlecture.test.arch.tasklet;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
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
        System.out.println("----- tasklet -----");
        // 이것 저것 조회
        List<Long> memberNos = new ArrayList<>(List.of(9001L, 9002L, 9003L, 9004L, 9005L, 9006L, 9007L, 9008L, 9009L, 9010L));
        contribution.getStepExecution().getJobExecution().getExecutionContext().put("memberNos", memberNos);
        return RepeatStatus.FINISHED;
    }
}
