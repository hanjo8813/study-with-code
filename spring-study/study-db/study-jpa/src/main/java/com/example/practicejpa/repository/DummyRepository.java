package com.example.practicejpa.repository;

import com.example.practicejpa.entity.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<Dummy, Long> {

}
