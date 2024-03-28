package com.example.practicedummy.controllertest.zdt;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zdt")
//@Tag(name = "zdt")
public class ZonedDateTimeController {

    @PostMapping
    public ZonedDateTimeDto testSerialize(@RequestBody ZonedDateTimeDto dto) {
        System.out.println(dto.getZdt());
        return dto;
    }
}
