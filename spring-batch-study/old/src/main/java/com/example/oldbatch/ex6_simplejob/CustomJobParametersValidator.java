package com.example.oldbatch.ex6_simplejob;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

/**
 * job 시작 전에 동작함!
 */
public class CustomJobParametersValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String tempKey = parameters.getString("tempKey");
        if(!tempKey.equals("hi")){
            System.out.println("파라미터 달라요");
            throw new JobParametersInvalidException("tempKey 파라미터 값 잘못됨~");
        }
    }
}
