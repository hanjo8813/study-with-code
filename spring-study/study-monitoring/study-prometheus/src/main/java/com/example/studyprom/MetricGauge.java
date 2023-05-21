package com.example.studyprom;

import io.micrometer.core.instrument.Metrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class MetricGauge {

    private AtomicInteger num = new AtomicInteger(1);
    private ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));


    @PostConstruct
    public void init() {
        Metrics.gauge("custom.metric.gauge.num", CustomTag.tag1, num);
        Metrics.gauge("custom.metric.gauge.list", CustomTag.tag1, list, List::size);
    }

    public void plusNum() {
        num.addAndGet(1);
        System.out.println(num);
    }

    public void addList() {
        list.add(0);
    }

}
