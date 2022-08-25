package com.example.practicedummy.controller.b_응답wrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/b")
@RestController
public class ControllerB {
    @GetMapping("/1")
    public LocalDateTime test1(){
        LocalDateTime temp = LocalDateTime.now();
        System.out.println(temp);
        return temp;
    }

    @GetMapping("/2")
    public int test2(){
        return 1;
    }

    @GetMapping("/3")
    public ResponseEntity<Integer> test3(){
        return ResponseEntity.ok(1);
    }

    @GetMapping("/4")
    public ResponseEntity<Void> test4(){
        return ResponseEntity.notFound().build();
    }

}
