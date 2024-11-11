package com.example.PigeonsVoyageurs.loft;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface LoftRepository extends MongoRepository<Loft, String> {
    Optional<Loft> findByName(String name);
}

