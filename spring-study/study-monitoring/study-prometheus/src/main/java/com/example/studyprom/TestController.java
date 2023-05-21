package com.example.studyprom;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MetricGauge metricGauge;

    @GetMapping("/counter")
    public double counter() {
        Counter counter = Metrics.counter("custom.metric.counter", CustomTag.tag1);
        counter.increment();
        return counter.count();
    }

    @GetMapping("/gauge/num")
    public void gaugeNum() {
        metricGauge.plusNum();
    }

    @GetMapping("/gauge/list")
    public void gaugeList() {
        metricGauge.addList();
    }
}
