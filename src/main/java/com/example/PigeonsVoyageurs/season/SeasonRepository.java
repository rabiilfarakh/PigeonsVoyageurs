package com.example.PigeonsVoyageurs.season;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SeasonRepository extends MongoRepository<Season, String> {
    Optional<Season> findByName(String name);
}

