package com.example.PigeonsVoyageurs.organiser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganiserRepository extends MongoRepository<Organiser, Long> {
    Optional<Organiser> findByEmail(String email);
}
