package com.example.studyprom.counter;

import com.example.studyprom.CustomTag;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class CounterMetric2 {

    @PostConstruct
    public void init() {
        // micrometer 빌더 사용방식
        Counter.builder(CounterName.NAME_CUSTOM_METRIC_COUNTER2)
                .tags(CustomTag.tag1)
                .description("주석")  // actuator 호출시 주석 붙음
                .register(Metrics.globalRegistry);

        // prometheus 사용방식 (tag 사용 불가..)
//        io.prometheus.client.Counter.build()
//                .name("custom.metric.counter3")
//                .help("주석")
//                .register();
    }

    public void increment() {
        Metrics.counter(CounterName.NAME_CUSTOM_METRIC_COUNTER2, CustomTag.tag1).increment();
    }
}
