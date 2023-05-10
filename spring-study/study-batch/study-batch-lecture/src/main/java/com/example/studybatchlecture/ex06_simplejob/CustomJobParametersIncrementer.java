package com.example.studybatchlecture.ex06_simplejob;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

import java.time.LocalDateTime;

/**
 * 스프링이 직접 job을 실행할때, 시작 전 파라미터를 인위적으로 추가해주는 방식이다.
 * 즉 실행주체가 다르다면 이 코드는 실행되지 않음.
 */
public class CustomJobParametersIncrementer implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters parameters) {
        return new JobParametersBuilder()
                .addString("param_id", LocalDateTime.now().toString())
                .toJobParameters();
    }
}
