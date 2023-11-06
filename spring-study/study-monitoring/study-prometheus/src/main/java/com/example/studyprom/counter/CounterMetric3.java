package com.example.studyprom.counter;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * static util 클래스
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CounterMetric3 {

    public static void increment(String label) {
        Tags tags = Tags.of(Tag.of("label", label));
        Metrics.counter(CounterName.NAME_CUSTOM_METRIC_COUNTER3, tags);
    }
}
