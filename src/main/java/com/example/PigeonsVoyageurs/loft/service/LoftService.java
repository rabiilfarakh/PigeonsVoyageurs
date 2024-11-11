package com.example.PigeonsVoyageurs.loft.service;

import com.example.PigeonsVoyageurs.loft.dto.LoftRequestDTO;
import com.example.PigeonsVoyageurs.loft.dto.LoftResponseDTO;

import java.util.List;
import java.util.Optional;

public interface LoftService {
    LoftResponseDTO save(LoftRequestDTO loftDTO);

    Optional<LoftResponseDTO> findById(String id);

    Optional<LoftResponseDTO> findByName(String email);

    List<LoftResponseDTO> findAll();

    LoftResponseDTO update(String id, LoftRequestDTO loftDTO);

    void delete(String id);
}
