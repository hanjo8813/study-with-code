package com.example.studyprom.timer;

import com.example.studyprom.CustomTag;
import io.micrometer.core.instrument.Metrics;
import java.time.Duration;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class MetricTimer {

    public void record(LocalTime start) {
        LocalTime end = LocalTime.now();
        Metrics.timer("custom.metric.timer", CustomTag.tag1)
                .record(Duration.between(start, end));
    }

}
