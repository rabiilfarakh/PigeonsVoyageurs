package com.example.PigeonsVoyageurs.loft;

import com.example.PigeonsVoyageurs.loft.dto.LoftRequestDTO;
import com.example.PigeonsVoyageurs.loft.dto.LoftResponseDTO;
import com.example.PigeonsVoyageurs.loft.service.LoftServiceImpl;
import com.example.PigeonsVoyageurs.loft.dto.LoftResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lofts")
public class LoftController {

    private final LoftServiceImpl loftService;

    @Autowired

    public LoftController(LoftServiceImpl loftService) {
        this.loftService = loftService;
    }

    @PostMapping
    public ResponseEntity<LoftResponseDTO> createLoft(@RequestBody LoftRequestDTO loftDTO){
        LoftResponseDTO createdLoft = loftService.save(loftDTO);
        return new ResponseEntity<>(createdLoft, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LoftResponseDTO> getLoftById(@PathVariable String id) {
        Optional<LoftResponseDTO> loft = loftService.findById(id);
        return new ResponseEntity<>(loft.orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<LoftResponseDTO> getLoftByName(@PathVariable String name) {
        Optional<LoftResponseDTO> loft = loftService.findByName(name);
        return new ResponseEntity<>(loft.orElseThrow(), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<LoftResponseDTO>> getAllLofts() {
        List<LoftResponseDTO> lofts = loftService.findAll();
        return new ResponseEntity<>(lofts, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable String id) {
        loftService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
