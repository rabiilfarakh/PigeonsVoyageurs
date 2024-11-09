package com.example.PigeonsVoyageurs.season.service;

import com.example.PigeonsVoyageurs.season.dto.SeasonRequestDTO;
import com.example.PigeonsVoyageurs.season.dto.SeasonResponseDTO;

import java.util.List;

public interface SeasonService {
    SeasonResponseDTO save(SeasonRequestDTO seasonDTO);

    SeasonResponseDTO findById(String id);

    List<SeasonResponseDTO> findAll();

    void delete(String id);
}
