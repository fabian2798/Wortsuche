package com.example.Wortsuche;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VorlagenRepository extends MongoRepository<Vorlagen, ObjectId> {
    Optional<Vorlagen> findByTitle(String title);
}
