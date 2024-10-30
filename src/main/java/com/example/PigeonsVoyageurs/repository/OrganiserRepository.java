package com.example.PigeonsVoyageurs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.PigeonsVoyageurs.entity.Organiser;

public interface OrganiserRepository extends MongoRepository<Organiser, Long> {
}

