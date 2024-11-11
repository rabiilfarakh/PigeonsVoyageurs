package com.example.PigeonsVoyageurs.breeder;

import com.example.PigeonsVoyageurs.breeder.dto.BreederRequestDTO;
import com.example.PigeonsVoyageurs.breeder.dto.BreederResponseDTO;
import com.example.PigeonsVoyageurs.breeder.service.BreederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/breeder")
public class BreederController {
    
    private final BreederService breederService;

    @Autowired
    public BreederController(BreederService breederService) {
        this.breederService = breederService;
    }

    @PostMapping("/register")
    public ResponseEntity<BreederResponseDTO> register(@RequestBody BreederRequestDTO breederDTO) {
        BreederResponseDTO breeder = breederService.register(breederDTO);
        return ResponseEntity.ok(breeder);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<String> result = breederService.login(email, password);
        return result.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreederResponseDTO> findById(@PathVariable String id) {
        Optional<BreederResponseDTO> breederOpt = breederService.findById(id);
        return breederOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BreederResponseDTO> findByEmail(@PathVariable String email) {
        Optional<BreederResponseDTO> breederOpt = breederService.findByEmail(email);
        return breederOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BreederResponseDTO>> findAll() {
        List<BreederResponseDTO> breeders = breederService.findAll();
        return ResponseEntity.ok(breeders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreederResponseDTO> update(@PathVariable String id, @RequestBody BreederRequestDTO breederDTO) {
        BreederResponseDTO updatedBreeder = breederService.update(id, breederDTO);
        return ResponseEntity.ok(updatedBreeder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        breederService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
