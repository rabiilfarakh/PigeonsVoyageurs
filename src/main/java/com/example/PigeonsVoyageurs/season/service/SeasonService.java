package com.example.PigeonsVoyageurs.season.service;

import com.example.PigeonsVoyageurs.season.Season;
import com.example.PigeonsVoyageurs.season.dto.SeasonRequestDTO;
import com.example.PigeonsVoyageurs.season.dto.SeasonResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SeasonService {
    SeasonResponseDTO save(SeasonRequestDTO seasonDTO);

    SeasonResponseDTO findById(String id);

    List<SeasonResponseDTO> findAll();

    void delete(String id);

    public Optional<Season> findByName(String name);
}
