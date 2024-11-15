package com.example.PigeonsVoyageurs.ranking.service;

import com.example.PigeonsVoyageurs.ranking.Ranking;
import com.example.PigeonsVoyageurs.ranking.RankingMapper;
import com.example.PigeonsVoyageurs.ranking.RankingRepository;
import com.example.PigeonsVoyageurs.ranking.dto.RankingRequestDTO;
import com.example.PigeonsVoyageurs.ranking.dto.RankingResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;

    public RankingServiceImpl(RankingRepository rankingRepository, RankingMapper rankingMapper) {
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
    }

    @Override
    public RankingResponseDTO save(RankingRequestDTO rankingDTO) {
        Ranking ranking = rankingMapper.toEntity(rankingDTO);
        Ranking savedRanking = rankingRepository.save(ranking);
        return rankingMapper.toDTO(savedRanking);
    }

    @Override
    public RankingResponseDTO findById(String id) {
        Ranking ranking = rankingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ranking not found"));
        return rankingMapper.toDTO(ranking);
    }

    @Override
    public List<RankingResponseDTO> findAll() {
        return rankingRepository.findAll().stream()
                .map(rankingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if (!rankingRepository.existsById(id)) {
            throw new RuntimeException("Ranking not found");
        }
        rankingRepository.deleteById(id);
    }
}
