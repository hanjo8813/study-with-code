package com.example.mvc.repository;

import com.example.mvc.document.MongoDummy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDummyRepository extends MongoRepository<MongoDummy, String> {

}
