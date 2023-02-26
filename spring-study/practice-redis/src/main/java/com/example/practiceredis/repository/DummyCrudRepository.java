package com.example.practiceredis.repository;

import com.example.practiceredis.entity.Dummy;
import org.springframework.data.repository.CrudRepository;

public interface DummyCrudRepository extends CrudRepository<Dummy, String> {
}
