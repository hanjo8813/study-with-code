package com.example.studyprom.gauge;

import com.example.studyprom.CustomTag;
import io.micrometer.core.instrument.Metrics;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;

//@Component
public class MetricGauge2 {

    private AtomicInteger num;
    private List<Integer> list;

    /**
     * 등록 + 초기화 함께 사용하는 방식
     * 그냥 해봤는데 잘된다. 이렇게 사용할일이 있을까 싶음
     */
    @PostConstruct
    public void init() {
        this.num = Metrics.gauge("custom.metric.gauge2.num", CustomTag.tag1, new AtomicInteger(1));
        this.list = Metrics.gauge("custom.metric.gauge2.list", CustomTag.tag1, List.of(1, 2, 3), List::size);
    }

    public void plusNum() {
        num.addAndGet(1);
    }

    public void addList() {
        list.add(0);
    }
}
