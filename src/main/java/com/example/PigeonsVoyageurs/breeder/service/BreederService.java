package com.example.PigeonsVoyageurs.breeder.service;

import com.example.PigeonsVoyageurs.breeder.dto.BreederRequestDTO;
import com.example.PigeonsVoyageurs.breeder.dto.BreederResponseDTO;

import java.util.List;
import java.util.Optional;

public interface BreederService {
    BreederResponseDTO register(BreederRequestDTO breederDTO);

    Optional<String> login(String userName, String password);

    Optional<BreederResponseDTO> findById(String id);

    Optional<BreederResponseDTO> findByEmail(String email);

    List<BreederResponseDTO> findAll();

    BreederResponseDTO update(String id, BreederRequestDTO breederDTO);

    void delete(String id);
}
