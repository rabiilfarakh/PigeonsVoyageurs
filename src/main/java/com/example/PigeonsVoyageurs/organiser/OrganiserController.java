package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.organiser.service.OrganiserService;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<Organiser> register(@RequestBody OrganiserDTO organiserDTO) {
        Organiser organiser = organiserService.register(organiserDTO);
        return ResponseEntity.ok(organiser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Optional<String> result = organiserService.login(email, password);
        return result.map(user -> {
                    session.setAttribute("user", user);
                    return ResponseEntity.ok("Logged in successfully");
                })
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid credentials"));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return ResponseEntity.status(403).body("User is not authenticated");
        }

        Optional<Organiser> organiserOpt = organiserService.findById(id);
        return organiserOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email, HttpSession session) {
        if (!isAuthenticated(session)) {
            return ResponseEntity.status(403).body("User is not authenticated");
        }

        Optional<Organiser> organiserOpt = organiserService.findByEmail(email);
        return organiserOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> findAll(HttpSession session) {
        if (!isAuthenticated(session)) {
            return ResponseEntity.status(403).body("User is not authenticated");
        }

        List<Organiser> organisers = organiserService.findAll();
        return ResponseEntity.ok(organisers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody OrganiserDTO organiserDTO, HttpSession session) {
        if (!isAuthenticated(session)) {
            return ResponseEntity.status(403).body("User is not authenticated");
        }

        Organiser updatedOrganiser = organiserService.update(id, organiserDTO);
        return ResponseEntity.ok(updatedOrganiser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id, HttpSession session) {
        if (!isAuthenticated(session)) {
            return ResponseEntity.status(403).body("User is not authenticated");
        }

        organiserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
