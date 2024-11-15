package com.example.PigeonsVoyageurs.ranking;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RankingRepository extends MongoRepository<Ranking, String> {
}
