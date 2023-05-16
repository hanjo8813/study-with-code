package com.example.studyprom;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @PostConstruct
    public void postConstruct() {
        List<Tag> tags = List.of(
                Tag.of("custom_label_a", "value_a"),
                Tag.of("custom_label_b", "value_b")
        );

        Metrics.counter("custom.metric.A", tags).increment();
    }
}
