package com.example.PigeonsVoyageurs.season.service;

import com.example.PigeonsVoyageurs.season.Season;
import com.example.PigeonsVoyageurs.season.SeasonMapper;
import com.example.PigeonsVoyageurs.season.SeasonRepository;
import com.example.PigeonsVoyageurs.season.dto.SeasonRequestDTO;
import com.example.PigeonsVoyageurs.season.dto.SeasonResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository, SeasonMapper seasonMapper) {
        this.seasonRepository = seasonRepository;
        this.seasonMapper = seasonMapper;
    }
    @Override
    public SeasonResponseDTO save(SeasonRequestDTO seasonDTO) {
        Season season = seasonMapper.toEntity(seasonDTO);
        season = seasonRepository.save(season);
        return seasonMapper.toResponseDTO(season);
    }

    @Override
    public SeasonResponseDTO findById(String id) {
        return seasonRepository.findById(id)
                .map(seasonMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Season not found"));
    }

    @Override
    public List<SeasonResponseDTO> findAll() {
        return seasonRepository.findAll().stream()
                .map(seasonMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        seasonRepository.deleteById(id);
    }

    public Optional<Season> findByName(String name) {
        return seasonRepository.findByName(name);
    }
}
