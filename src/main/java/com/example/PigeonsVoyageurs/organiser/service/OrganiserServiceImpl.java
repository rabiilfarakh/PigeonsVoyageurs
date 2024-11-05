package com.example.PigeonsVoyageurs.organiser.service;

import com.example.PigeonsVoyageurs.organiser.Organiser;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserRequestDTO;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserResponseDTO;
import com.example.PigeonsVoyageurs.organiser.OrganiserMapper;
import com.example.PigeonsVoyageurs.organiser.OrganiserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganiserServiceImpl implements OrganiserService {

    private final OrganiserRepository organiserRepository;
    private final OrganiserMapper organiserMapper;

    @Autowired
    public OrganiserServiceImpl(OrganiserRepository organiserRepository, OrganiserMapper organiserMapper) {
        this.organiserRepository = organiserRepository;
        this.organiserMapper = organiserMapper;
    }

    @Override
    public OrganiserResponseDTO register(OrganiserRequestDTO organiserDTO) {
        String hashedPassword = BCrypt.hashpw(organiserDTO.password(), BCrypt.gensalt());
        Organiser organiser = organiserMapper.toEntity(organiserDTO);
        organiser.setPassword(hashedPassword);
        Organiser savedOrganiser = organiserRepository.save(organiser);
        return organiserMapper.toResponseDTO(savedOrganiser);
    }

    @Override
    public Optional<String> login(String email, String password) {
        Optional<Organiser> organiserOpt = organiserRepository.findByEmail(email);
        if (organiserOpt.isPresent()) {
            Organiser organiser = organiserOpt.get();
            if (BCrypt.checkpw(password, organiser.getPassword())) {
                return Optional.of("Login successful");
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<OrganiserResponseDTO> findById(String id) {
        return organiserRepository.findById(id)
                .map(organiserMapper::toResponseDTO);
    }

    @Override
    public Optional<OrganiserResponseDTO> findByEmail(String email) {
        return organiserRepository.findByEmail(email)
                .map(organiserMapper::toResponseDTO);
    }

    @Override
    public List<OrganiserResponseDTO> findAll() {
        return organiserRepository.findAll().stream()
                .map(organiserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrganiserResponseDTO update(String id, OrganiserRequestDTO organiserDTO) {
        Organiser organiser = organiserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organiser not found"));

        organiser.setUserName(organiserDTO.userName());

        if (organiserDTO.password() != null && !organiserDTO.password().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(organiserDTO.password(), BCrypt.gensalt());
            organiser.setPassword(hashedPassword);
        }

        organiser.setEmail(organiserDTO.email());
        Organiser updatedOrganiser = organiserRepository.save(organiser);

        return organiserMapper.toResponseDTO(updatedOrganiser);
    }

    @Override
    public void delete(String id) {
        organiserRepository.deleteById(id);
    }
}
