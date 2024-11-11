package com.example.PigeonsVoyageurs.breeder.service;

import com.example.PigeonsVoyageurs.breeder.BreederMapper;
import com.example.PigeonsVoyageurs.breeder.BreederRepository;
import com.example.PigeonsVoyageurs.breeder.dto.BreederRequestDTO;
import com.example.PigeonsVoyageurs.breeder.dto.BreederResponseDTO;
import com.example.PigeonsVoyageurs.breeder.Breeder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BreederServiceImpl implements BreederService {

    private final BreederRepository breederRepository;
    private final BreederMapper breederMapper;
    
    @Autowired
    public BreederServiceImpl(BreederRepository breederRepository, BreederMapper breederMapper) {
        this.breederRepository = breederRepository;
        this.breederMapper = breederMapper;
    }

    @Override
    public BreederResponseDTO register(BreederRequestDTO breederDTO) {
        String hashedPassword = BCrypt.hashpw(breederDTO.password(), BCrypt.gensalt());
        Breeder breeder = breederMapper.toEntity(breederDTO);
        breeder.setPassword(hashedPassword);
        Breeder savedBreeder = breederRepository.save(breeder);
        return breederMapper.toResponseDTO(savedBreeder);
    }

    @Override
    public Optional<String> login(String email, String password) {
        Optional<Breeder> breederOpt = breederRepository.findByEmail(email);
        if (breederOpt.isPresent()) {
            Breeder breeder = breederOpt.get();
            if (BCrypt.checkpw(password, breeder.getPassword())) {
                return Optional.of("Login successful");
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<BreederResponseDTO> findById(String id) {
        return breederRepository.findById(id)
                .map(breederMapper::toResponseDTO);
    }

    @Override
    public Optional<BreederResponseDTO> findByEmail(String email) {
        return breederRepository.findByEmail(email)
                .map(breederMapper::toResponseDTO);
    }

    @Override
    public List<BreederResponseDTO> findAll() {
        return breederRepository.findAll().stream()
                .map(breederMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BreederResponseDTO update(String id, BreederRequestDTO breederDTO) {
        Breeder breeder = breederRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Breeder not found"));

        breeder.setUserName(breederDTO.userName());

        if (breederDTO.password() != null && !breederDTO.password().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(breederDTO.password(), BCrypt.gensalt());
            breeder.setPassword(hashedPassword);
        }

        breeder.setEmail(breederDTO.email());
        Breeder updatedBreeder = breederRepository.save(breeder);

        return breederMapper.toResponseDTO(updatedBreeder);
    }

    @Override
    public void delete(String id) {
        breederRepository.deleteById(id);
    }
}
