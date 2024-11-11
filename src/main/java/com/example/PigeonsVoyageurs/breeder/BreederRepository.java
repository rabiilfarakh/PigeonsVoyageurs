package com.example.PigeonsVoyageurs.breeder;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BreederRepository extends MongoRepository<Breeder, String> {
    Optional<Breeder> findByEmail(String email);
}

