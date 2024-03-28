package com.example.practicedummy.controllertest.basic.repository;

import com.example.practicedummy.controllertest.basic.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<Dummy, Long> {
}
