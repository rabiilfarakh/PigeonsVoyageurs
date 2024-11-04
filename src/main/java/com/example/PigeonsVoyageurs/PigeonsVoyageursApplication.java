package com.example.PigeonsVoyageurs;

import com.example.PigeonsVoyageurs.competition.Competition;
import com.example.PigeonsVoyageurs.competition.CompetitionRepository;
import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class PigeonsVoyageursApplication {

	public static void main(String[] args) {

		SpringApplication.run(PigeonsVoyageursApplication.class, args);
	}



	@Configuration
	public class CompetitionDataLoader {

//		@Bean
//		public CommandLineRunner loadCompetitions(CompetitionRepository competitionRepository) {
//			return args -> {
//				Competition competition1 = new Competition();
//				competition1.setRaceName("Course de Printemps");
//				competition1.setReleasePoint(new Coordinate(34.0522, -118.2437)); // Exemple de coordonnées
//				competition1.setStart(new Date());
//				competition1.setPreviewedDistance(500.0);
//
//				Competition competition2 = new Competition();
//				competition2.setRaceName("Course d'Été");
//				competition2.setReleasePoint(new Coordinate(40.7128, -74.0060)); // Exemple de coordonnées
//				competition2.setStart(new Date());
//				competition2.setPreviewedDistance(700.0);
//
//				Competition competition3 = new Competition();
//				competition3.setRaceName("Course d'Automne");
//				competition3.setReleasePoint(new Coordinate(51.5074, -0.1278)); // Exemple de coordonnées
//				competition3.setStart(new Date());
//				competition3.setPreviewedDistance(600.0);
//
//				// Sauvegarder les compétitions dans le repository
//				competitionRepository.saveAll(Arrays.asList(competition1, competition2, competition3));
//
//				System.out.println("Compétitions initiales insérées dans la base de données.");
//			};
//		}
	}


}