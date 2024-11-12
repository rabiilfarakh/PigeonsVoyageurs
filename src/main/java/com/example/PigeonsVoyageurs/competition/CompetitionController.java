package com.example.PigeonsVoyageurs.competition;

import com.example.PigeonsVoyageurs.competition.dto.CompetitionRequestDTO;
import com.example.PigeonsVoyageurs.competition.dto.CompetitionResponseDTO;
import com.example.PigeonsVoyageurs.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping
    public ResponseEntity<CompetitionResponseDTO> createCompetition(@RequestBody CompetitionRequestDTO competitionRequestDTO) {
        CompetitionResponseDTO competitionResponse = competitionService.save(competitionRequestDTO);
        return new ResponseEntity<>(competitionResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> getCompetitionById(@PathVariable String id) {
        CompetitionResponseDTO competitionResponse = competitionService.findById(id);
        return ResponseEntity.ok(competitionResponse);
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDTO>> getAllCompetitions() {
        List<CompetitionResponseDTO> competitions = competitionService.findAll();
        return ResponseEntity.ok(competitions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> updateCompetition(@PathVariable String id, @RequestBody CompetitionRequestDTO competitionRequestDTO) {
        CompetitionResponseDTO updatedCompetition = competitionService.save(competitionRequestDTO);
        return ResponseEntity.ok(updatedCompetition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable String id) {
        competitionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
