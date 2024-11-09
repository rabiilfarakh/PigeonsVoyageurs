package com.example.PigeonsVoyageurs.season;

import com.example.PigeonsVoyageurs.season.dto.SeasonRequestDTO;
import com.example.PigeonsVoyageurs.season.dto.SeasonResponseDTO;
import com.example.PigeonsVoyageurs.season.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {

    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @PostMapping
    public ResponseEntity<SeasonResponseDTO> createSeason(@RequestBody SeasonRequestDTO seasonDTO) {
        SeasonResponseDTO createdSeason = seasonService.save(seasonDTO);
        return new ResponseEntity<>(createdSeason, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonResponseDTO> getSeasonById(@PathVariable String id) {
        SeasonResponseDTO season = seasonService.findById(id);
        return new ResponseEntity<>(season, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SeasonResponseDTO>> getAllSeasons() {
        List<SeasonResponseDTO> seasons = seasonService.findAll();
        return new ResponseEntity<>(seasons, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable String id) {
        seasonService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

