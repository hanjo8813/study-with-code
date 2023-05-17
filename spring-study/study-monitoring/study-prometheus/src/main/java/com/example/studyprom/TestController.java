package com.example.studyprom;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/counter")
    public double counter() {
        Counter counter = Metrics.counter("custom.metric.counter", CustomTag.tag1);
        counter.increment();
        return counter.count();
    }


    int num = 2;

    @GetMapping("/gauge/num")
    public Integer gaugeNum() {
        Integer gauge = Metrics.gauge("custom.metric.gauge.num", CustomTag.tag1, num);
        return gauge;
    }


    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    @GetMapping("/gauge/list")
    public double gaugeList() {
        // Counter와 달리 새 객체로 덮어씌운다는 느낌이 있음
        Gauge newGauge = Gauge.builder("custom.metric.gauge.list", list, List::size)
                .tags(CustomTag.tag1)
                .register(Metrics.globalRegistry);
        return newGauge.value();

//        ArrayList<Integer> newList = Metrics.gauge("custom.metric.gauge.list", CustomTag.tag1, list, List::size);
//        return newList.size();
    }
}
