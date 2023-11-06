package com.example.studyprom.counter;

import com.example.studyprom.CustomTag;
import io.micrometer.core.instrument.Metrics;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class CounterMetric1 {

    /**
     * Counter는 metric + tag 만 잘 찾으면 전역으로도 사용 가능
     */
    @PostConstruct
    public void init() {
        // micrometer 스태틱 사용방식
        Metrics.counter(CounterName.NAME_CUSTOM_METRIC_COUNTER1, CustomTag.tag1);
    }
}
