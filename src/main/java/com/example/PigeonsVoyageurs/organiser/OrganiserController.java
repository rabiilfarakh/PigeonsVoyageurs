package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.organiser.dto.OrganiserRequestDTO;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserResponseDTO;
import com.example.PigeonsVoyageurs.organiser.service.OrganiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organisers")
public class OrganiserController {

    private final OrganiserService organiserService;

    @Autowired
    public OrganiserController(OrganiserService organiserService) {
        this.organiserService = organiserService;
    }

    @PostMapping("/register")
    public ResponseEntity<OrganiserResponseDTO> register(@RequestBody OrganiserRequestDTO organiserDTO) {
        OrganiserResponseDTO organiser = organiserService.register(organiserDTO);
        return ResponseEntity.ok(organiser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<String> result = organiserService.login(email, password);
        return result.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganiserResponseDTO> findById(@PathVariable String id) {
        Optional<OrganiserResponseDTO> organiserOpt = organiserService.findById(id);
        return organiserOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<OrganiserResponseDTO> findByEmail(@PathVariable String email) {
        Optional<OrganiserResponseDTO> organiserOpt = organiserService.findByEmail(email);
        return organiserOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<OrganiserResponseDTO>> findAll() {
        List<OrganiserResponseDTO> organisers = organiserService.findAll();
        return ResponseEntity.ok(organisers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganiserResponseDTO> update(@PathVariable String id, @RequestBody OrganiserRequestDTO organiserDTO) {
        OrganiserResponseDTO updatedOrganiser = organiserService.update(id, organiserDTO);
        return ResponseEntity.ok(updatedOrganiser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        organiserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
