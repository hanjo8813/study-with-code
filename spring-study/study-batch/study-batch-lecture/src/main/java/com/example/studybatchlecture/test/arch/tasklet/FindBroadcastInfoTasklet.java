package com.example.studybatchlecture.test.arch.tasklet;

import com.example.studybatchlecture.test.arch.constant.AlarmConstant;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class FindBroadcastInfoTasklet implements Tasklet {

    @Value("#{jobParameters[T(com.example.studybatchlecture.test.arch.constant.AlarmConstant).BROADCAST_SEQ]}")
    private Long broadcastSeq;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("----- FindBroadcastInfoTasklet -----");

        // 방송 정보 조회 -> context 저장
        System.out.println(broadcastSeq);

        contribution.getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put(AlarmConstant.BROADCAST_SEQ, broadcastSeq);

//        throw new RuntimeException();
        return RepeatStatus.FINISHED;
    }
}
