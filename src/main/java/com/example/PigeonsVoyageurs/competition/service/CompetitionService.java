package com.example.PigeonsVoyageurs.competition.service;

import com.example.PigeonsVoyageurs.competition.dto.CompetitionResponseDTO;
import com.example.PigeonsVoyageurs.competition.dto.CompetitionRequestDTO;

import java.util.List;

public interface CompetitionService {

    CompetitionResponseDTO save(CompetitionRequestDTO seasonDTO);

    CompetitionResponseDTO findById(String id);

    List<CompetitionResponseDTO> findAll();

    void delete(String id);
}
