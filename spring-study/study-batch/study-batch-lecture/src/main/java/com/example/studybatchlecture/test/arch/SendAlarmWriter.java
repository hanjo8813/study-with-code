package com.example.studybatchlecture.test.arch;

import java.util.List;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class SendAlarmWriter implements ItemWriter<Long> {

    @Override
    public void write(List<? extends Long> items) throws Exception {
        System.out.println("----- write -----");
        // 발송
        items.forEach(System.out::println);
    }
}
