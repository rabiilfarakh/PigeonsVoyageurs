package com.example.PigeonsVoyageurs.competition;

import com.example.PigeonsVoyageurs.competition.dto.CompetitionRequestDTO;
import com.example.PigeonsVoyageurs.competition.dto.CompetitionResponseDTO;
import com.example.PigeonsVoyageurs.competition.service.CompetitionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompetitionServiceImplTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private CompetitionMapper competitionMapper;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    private Competition competition;
    private CompetitionRequestDTO requestDTO;
    private CompetitionResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        competition = new Competition();
        competition.setRaceName("Race Test");
        competition = mockCompetitionWithId("comp123", competition);

        requestDTO = new CompetitionRequestDTO("Race Test", null, null, 500.0, "season123", null);
        responseDTO = new CompetitionResponseDTO("comp123", "Race Test", null, null, 500.0, null, null);
    }

    private Competition mockCompetitionWithId(String id, Competition competition) {
        when(competitionRepository.save(any(Competition.class))).thenAnswer(invocation -> {
            Competition savedCompetition = invocation.getArgument(0);
            return savedCompetition;
        });
        return competition;
    }

    @Test
    void testSave() {

        when(competitionMapper.toEntity(requestDTO)).thenReturn(competition);
        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);

        CompetitionResponseDTO result = competitionService.save(requestDTO);

        assertNotNull(result);
        assertEquals("comp123", result.id());
        assertEquals("Race Test", result.raceName());
        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void testFindById() {
        // Arrange
        when(competitionRepository.findById("comp123")).thenReturn(Optional.of(competition));
        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);

        CompetitionResponseDTO result = competitionService.findById("comp123");

        assertNotNull(result);
        assertEquals("comp123", result.id());
        assertEquals("Race Test", result.raceName());
        verify(competitionRepository, times(1)).findById("comp123");
    }

    @Test
    void testFindAll() {
        List<Competition> competitions = List.of(competition);
        List<CompetitionResponseDTO> responseDTOs = List.of(responseDTO);

        when(competitionRepository.findAll()).thenReturn(competitions);
        when(competitionMapper.toResponseDTO(competition)).thenReturn(responseDTO);

        List<CompetitionResponseDTO> result = competitionService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("comp123", result.get(0).id());
        assertEquals("Race Test", result.get(0).raceName());
        verify(competitionRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        String id = "comp123";
        competitionService.delete(id);
        verify(competitionRepository, times(1)).deleteById(id);
    }

}
