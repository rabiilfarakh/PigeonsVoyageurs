package com.example.PigeonsVoyageurs.breeder;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BreederRepository extends MongoRepository<Breeder, Long> {
}

