package com.example.practicejpa;

import com.example.practicejpa.entity.Dummy;
import com.example.practicejpa.entity.Dummy1;
import com.example.practicejpa.entity.User;
import com.example.practicejpa.repository.Dummy1Repository;
import com.example.practicejpa.repository.DummyRepository;
import com.example.practicejpa.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DummyService {

    private final DummyRepository repository;
    private final Dummy1Repository repository1;

    public List<Dummy> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void save(Dummy dummy) {
        if("reject".equals(dummy.getStr())) {
            throw new RuntimeException();
        }
        repository.save(dummy);
    }

    @Transactional
    public void multipleSave(Dummy dummy) {
        Dummy temp = Dummy.builder()
                .num(999)
                .str("temp")
                .build();
        repository.save(temp);

        save(dummy);
    }

    public List<Dummy1> findMapped() {
        return repository1.findAll();
    }
}
