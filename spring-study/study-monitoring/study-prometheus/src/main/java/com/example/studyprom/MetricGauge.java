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

    /**
     * gauge는 객체를 등록하고 해당 객체의 상태값을 뽑는 방식
     * 따라서 gauge metric을 등록할때 사용된 객체는 바꿀 수 없다 (전역으로 사용 불가 -> 덮어쓰기 불가)
     * 즉 가비지 컬렉터의 대상이 되지 않기 위해 하나의 클래스에서 메트릭을 제어하는게 편함
     */
    private AtomicInteger num = new AtomicInteger(1);
    private ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

    @PostConstruct
    public void init() {
        Metrics.gauge("custom.metric.gauge.num", CustomTag.tag1, num);
        Metrics.gauge("custom.metric.gauge.list", CustomTag.tag1, list, List::size);
    }

    public void plusNum() {
        num.addAndGet(1);
    }

    public void addList() {
        list.add(0);
    }

}
