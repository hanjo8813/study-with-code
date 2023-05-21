package com.example.studyprom;

import io.micrometer.core.instrument.Metrics;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MetricCounter {

    /**
     * Counter는 metric + tag 만 잘 찾으면 전역으로도 사용 가능
     */
    @PostConstruct
    public void init() {
        // micrometer 스태틱 사용방식
        Metrics.counter("custom.metric.counter", CustomTag.tag1);

        // micrometer 빌더 사용방식
//        Counter.builder("custom.metric.counter")
//                .tags(CustomTag.tag1)
//                .description("주석")
//                .register(Metrics.globalRegistry);

        // prometheus 사용방식 (tag 사용 불가..)
//        Counter.build()
//                .name("custom.metric.counter")
//                .help("주석")
//                .register();
    }
}
