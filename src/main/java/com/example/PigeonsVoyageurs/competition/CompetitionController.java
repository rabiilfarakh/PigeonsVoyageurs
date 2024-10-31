package com.example.PigeonsVoyageurs.competition;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionRepository competitionRepository;

    public CompetitionController(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @GetMapping
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Competition getCompetitionById(@PathVariable Long id) {
        return competitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Competition not found"));
    }

    @PostMapping
    public Competition createCompetition(@RequestBody Competition competition) {
        return competitionRepository.save(competition);
    }

    @DeleteMapping("/{id}")
    public String deleteCompetition(@PathVariable Long id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        competitionRepository.delete(competition);
        return "Competition with ID " + id + " has been deleted.";
    }
}