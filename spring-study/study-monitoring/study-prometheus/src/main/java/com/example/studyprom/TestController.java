package com.example.studyprom;

import com.example.studyprom.gauge.MetricGauge;
import com.example.studyprom.timer.MetricTimer;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MetricGauge metricGauge;
    private final MetricTimer metricTimer;

    @GetMapping("/counter")
    public double counter() {
        Counter counter = Metrics.counter("custom.metric.counter", CustomTag.tag1);
        counter.increment();
        return counter.count();
    }

    @GetMapping("/gauge/num")
    public void gaugeNum() {
        metricGauge.change();
    }

    @GetMapping("/gauge/list")
    public void gaugeList() {
        metricGauge.addList();
    }

    @GetMapping("/timer")
    public void timer() throws InterruptedException {
        LocalTime start = LocalTime.now();
        Thread.sleep(5000);
        metricTimer.record(start);
    }
}
