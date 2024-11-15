package com.example.PigeonsVoyageurs.ranking;

import com.example.PigeonsVoyageurs.ranking.dto.RankingRequestDTO;
import com.example.PigeonsVoyageurs.ranking.dto.RankingResponseDTO;
import com.example.PigeonsVoyageurs.ranking.service.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rankings")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping
    public ResponseEntity<RankingResponseDTO> save(@RequestBody RankingRequestDTO rankingDTO) {
        return ResponseEntity.ok(rankingService.save(rankingDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankingResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(rankingService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RankingResponseDTO>> findAll() {
        return ResponseEntity.ok(rankingService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        rankingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
