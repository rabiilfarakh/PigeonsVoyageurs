package com.example.PigeonsVoyageurs.pigeon.controller;

import com.example.PigeonsVoyageurs.pigeon.dto.PigeonRequestDTO;
import com.example.PigeonsVoyageurs.pigeon.dto.PigeonResponseDTO;
import com.example.PigeonsVoyageurs.pigeon.service.PigeonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pigeons")
public class PigeonController {

    private final PigeonService pigeonService;

    public PigeonController(PigeonService pigeonService) {
        this.pigeonService = pigeonService;
    }

    @PostMapping
    public ResponseEntity<PigeonResponseDTO> createPigeon(@RequestBody PigeonRequestDTO pigeonRequestDTO) {
        PigeonResponseDTO createdPigeon = pigeonService.save(pigeonRequestDTO);
        return new ResponseEntity<>(createdPigeon, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PigeonResponseDTO> getPigeonById(@PathVariable String id) {
        PigeonResponseDTO pigeon = pigeonService.findById(id);
        return new ResponseEntity<>(pigeon, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PigeonResponseDTO>> getAllPigeons() {
        List<PigeonResponseDTO> pigeons = pigeonService.findAll();
        return new ResponseEntity<>(pigeons, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePigeon(@PathVariable String id) {
        pigeonService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
