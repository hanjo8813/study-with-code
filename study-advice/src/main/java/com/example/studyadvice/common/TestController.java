package com.example.studyadvice.common;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/2")
    public Dummy test2() {
        return Dummy.of("test2", 2);
    }

    @GetMapping("/3")
    public ApiResponse<Dummy> test3() {
        return ApiResponse.of(Dummy.of("test3", 3));
    }

    @GetMapping("/4")
    public Long test4() {
        return 4L;
    }

    @GetMapping("/5")
    public List<String> test5() {
        return List.of("test5");
    }

    @GetMapping("/5-1")
    public List<Dummy> test5_1() {
        return List.of(Dummy.of("test5-1", 51));
    }

    @GetMapping("/6")
    public Map<String, String> test6() {
        return Map.of("test6", "test6");
    }

    @GetMapping("/7")
    public ResponseEntity<Void> test7() {
        return ResponseEntity.ok(null);
    }

}
