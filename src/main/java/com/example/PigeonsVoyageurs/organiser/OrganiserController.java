package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.organiser.service.OrganiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Organiser> register(@RequestBody OrganiserDTO organiserDTO) {
        Organiser organiser = organiserService.register(organiserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(organiser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<String> loginResult = organiserService.login(email, password);
        return loginResult.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organiser> findById(@PathVariable Long id) {
        Optional<Organiser> organiserOpt = organiserService.findById(id);
        return organiserOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Organiser>> findAll() {
        List<Organiser> organisers = organiserService.findAll();
        return ResponseEntity.ok(organisers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organiser> update(@PathVariable Long id, @RequestBody OrganiserDTO organiserDTO) {
        Organiser updatedOrganiser = organiserService.update(id, organiserDTO);
        return ResponseEntity.ok(updatedOrganiser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organiserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
