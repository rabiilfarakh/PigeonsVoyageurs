package com.example.PigeonsVoyageurs.competition.service;

import com.example.PigeonsVoyageurs.competition.Competition;
import com.example.PigeonsVoyageurs.competition.CompetitionMapper;
import com.example.PigeonsVoyageurs.competition.CompetitionRepository;
import com.example.PigeonsVoyageurs.competition.dto.CompetitionRequestDTO;
import com.example.PigeonsVoyageurs.competition.dto.CompetitionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService{

    private final CompetitionRepository seasonRepository;
    private final CompetitionMapper seasonMapper;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository seasonRepository, CompetitionMapper seasonMapper) {
        this.seasonRepository = seasonRepository;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public CompetitionResponseDTO save(CompetitionRequestDTO seasonDTO) {
        Competition season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toResponseDTO(season);
    }

    @Override
    public CompetitionResponseDTO findById(String id) {
        return seasonRepository.findById(id)
                .map(seasonMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
    }

    @Override
    public List<CompetitionResponseDTO> findAll() {
        return seasonRepository.findAll().stream()
                .map(seasonMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        seasonRepository.deleteById(id);
    }
}
