package com.example.practicejpa;

import com.example.practicejpa.entity.Dummy;
import java.util.List;
import javax.swing.plaf.DimensionUIResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dummy")
@RestController
@RequiredArgsConstructor
public class DummyController {

    private final DummyService service;

    @GetMapping("/all")
    public List<Dummy> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(@RequestBody Dummy dummy) {
        service.save(dummy);
    }

    @PostMapping("/multiple")
    public void multipleSave(@RequestBody Dummy dummy) {
        service.multipleSave(dummy);
    }
}
