package com.example.PigeonsVoyageurs.ranking;

import com.example.PigeonsVoyageurs.competition.Competition;
import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import com.example.PigeonsVoyageurs.ranking.Ranking;
import com.example.PigeonsVoyageurs.ranking.RankingRepository;
import com.example.PigeonsVoyageurs.ranking.dto.RankingRequestDTO;
import com.example.PigeonsVoyageurs.ranking.dto.RankingResponseDTO;

import com.example.PigeonsVoyageurs.ranking.service.RankingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RankingServiceImplTest {

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private RankingMapper rankingMapper;

    @InjectMocks
    private RankingServiceImpl rankingService;

    private Ranking ranking;
    private RankingRequestDTO rankingRequestDTO;
    private RankingResponseDTO rankingResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation de données fictives pour les tests
        Competition competition = new Competition();
        competition.setId("comp1");
        competition.setRaceName("Championnat");

        Pigeon pigeon = new Pigeon();
        pigeon.setPigeonRing("Pigeon1");

        ranking = new Ranking();
        ranking.setCompetition(competition);
        ranking.setPigeon(pigeon);
        ranking.setPosition(1);
        ranking.setDistance(300.0);
        ranking.setSpeed(80.0);

        rankingRequestDTO = new RankingRequestDTO(
                competition.getId(),
                pigeon.getPigeonRing(),
                ranking.getPosition(),
                ranking.getDistance(),
                ranking.getDuration(),
                ranking.getSpeed(),
                ranking.getScore()
        );

        rankingResponseDTO = new RankingResponseDTO(
                ranking.getId(),
                competition.getRaceName(),
                pigeon.getPigeonRing(),
                ranking.getPosition(),
                ranking.getDistance(),
                ranking.getDuration(),
                ranking.getSpeed(),
                ranking.getScore()
        );
    }

    @Test
    void testSaveRanking() {
        when(rankingMapper.toEntity(any(RankingRequestDTO.class))).thenReturn(ranking);
        when(rankingRepository.save(any(Ranking.class))).thenReturn(ranking);
        when(rankingMapper.toDTO(any(Ranking.class))).thenReturn(rankingResponseDTO);

        RankingResponseDTO result = rankingService.save(rankingRequestDTO);

        assertNotNull(result);
        assertEquals(rankingResponseDTO.id(), result.id());
        verify(rankingRepository, times(1)).save(ranking);
    }

    @Test
    void testFindRankingById() {
        when(rankingRepository.findById("rank1")).thenReturn(Optional.of(ranking));
        when(rankingMapper.toDTO(ranking)).thenReturn(rankingResponseDTO);

        RankingResponseDTO result = rankingService.findById("rank1");

        assertNotNull(result);
        assertEquals("rank1", result.id());
        verify(rankingRepository, times(1)).findById("rank1");
    }


    @Test
    void testFindAllRankings() {
        when(rankingRepository.findAll()).thenReturn(List.of(ranking));
        when(rankingMapper.toDTO(ranking)).thenReturn(rankingResponseDTO);

        List<RankingResponseDTO> results = rankingService.findAll();

        assertNotNull(results);
        assertEquals(1, results.size());
        verify(rankingRepository, times(1)).findAll();
    }

    @Test
    void testDeleteRanking() {
        when(rankingRepository.findById("rank1")).thenReturn(Optional.of(ranking));
        doNothing().when(rankingRepository).delete(ranking);

        rankingService.delete("rank1");

        verify(rankingRepository, times(1)).delete(ranking);
    }

    @Test
    void testDeleteRanking_NotFound() {
        when(rankingRepository.findById("rank1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> rankingService.delete("rank1"));
        verify(rankingRepository, never()).delete(any());
    }

}
