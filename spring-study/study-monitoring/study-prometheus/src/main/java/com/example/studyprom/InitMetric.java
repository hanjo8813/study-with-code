package com.example.studyprom;

import io.micrometer.core.instrument.Metrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InitMetric {

    @PostConstruct
    public void init() {
        initCounter();
        initGaugeNum();
        initGaugeList();
    }

    private void initCounter() {
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

    private void initGaugeNum() {
        // micrometer 스태틱 사용방식
        Metrics.gauge("custom.metric.gauge.num", CustomTag.tag1, 1);

        // micrometer 빌더는 숫자 지원 X
    }


    // 람다가 바라볼 수 있도록 반드시 전역 변수여야 함 !!!
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

    private void initGaugeList() {
        // micrometer 스태틱 사용방식
        Metrics.gauge("custom.metric.gauge.list", CustomTag.tag1, list, List::size);

        // micrometer 빌더 사용방식
//        Gauge.builder("custom.metric.gauge", list, List::size)
//                .tags(CustomTag.tag1)
//                .description("주석")
//                .register(Metrics.globalRegistry);
    }
}
