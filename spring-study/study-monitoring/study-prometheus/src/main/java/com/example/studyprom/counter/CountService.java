package com.example.studyprom.counter;

import com.example.studyprom.CustomTag;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CountService {

    private final CounterMetric2 counterMetric2;

    public void counter1() {
        Counter counter = Metrics.counter(CounterName.NAME_CUSTOM_METRIC_COUNTER1, CustomTag.tag1);
        counter.increment();

        // 메트릭명은 같은데 라벨이 다른 경우?? -> 새로 생성 안됨.
        Metrics.counter(CounterName.NAME_CUSTOM_METRIC_COUNTER1, CustomTag.tag3).increment();
    }

    public void counter2() {
        counterMetric2.increment();
    }

    public void counter3(String label) {
        CounterMetric3.increment(label);
    }

    // custom_metric_counter4_total{class="com.example.studyprom.counter.CountService",exception="none",method="counter4",result="success",}
    @Counted(value = CounterName.NAME_CUSTOM_METRIC_COUNTER4)
    public void counter4() {
    }
}
