package com.example.PigeonsVoyageurs.pigeon;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigeonRepository extends MongoRepository<Pigeon,String> {
}
