package com.example.PigeonsVoyageurs.season;

import com.example.PigeonsVoyageurs.season.dto.SeasonRequestDTO;
import com.example.PigeonsVoyageurs.season.dto.SeasonResponseDTO;
import com.example.PigeonsVoyageurs.season.service.SeasonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeasonServiceImplTest {

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private SeasonMapper seasonMapper;

    @InjectMocks
    private SeasonServiceImpl seasonService;

    private Season season;
    private SeasonRequestDTO seasonRequestDTO;
    private SeasonResponseDTO seasonResponseDTO;

    @BeforeEach
    void setUp() {
        season = new Season();
        season.setId("S123");
        season.setName("Spring 2024");
        season.setStartDate(LocalDateTime.of(2024, 3, 1, 0, 0));
        season.setEndDate(LocalDateTime.of(2024, 6, 1, 0, 0));

        seasonRequestDTO = new SeasonRequestDTO("Spring 2024", LocalDateTime.of(2024, 3, 1, 0, 0), LocalDateTime.of(2024, 6, 1, 0, 0));
        seasonResponseDTO = new SeasonResponseDTO("S123", "Spring 2024", LocalDateTime.of(2024, 3, 1, 0, 0), LocalDateTime.of(2024, 6, 1, 0, 0));

        // Lenient stubbing to prevent unnecessary stubbing exception
        lenient().when(seasonMapper.toEntity(any(SeasonRequestDTO.class))).thenReturn(season);
        lenient().when(seasonMapper.toResponseDTO(any(Season.class))).thenReturn(seasonResponseDTO);
    }

    @Test
    void testSave() {
        when(seasonRepository.save(any(Season.class))).thenReturn(season);

        SeasonResponseDTO result = seasonService.save(seasonRequestDTO);

        assertNotNull(result);
        assertEquals("S123", result.id());
        assertEquals("Spring 2024", result.name());
        assertEquals(LocalDateTime.of(2024, 3, 1, 0, 0), result.startDate());
        assertEquals(LocalDateTime.of(2024, 6, 1, 0, 0), result.endDate());
        verify(seasonRepository, times(1)).save(any(Season.class));
    }

    @Test
    void testFindById_whenSeasonExists() {
        when(seasonRepository.findById(anyString())).thenReturn(Optional.of(season));

        SeasonResponseDTO result = seasonService.findById("S123");

        assertNotNull(result);
        assertEquals("S123", result.id());
        assertEquals("Spring 2024", result.name());
        assertEquals(LocalDateTime.of(2024, 3, 1, 0, 0), result.startDate());
        assertEquals(LocalDateTime.of(2024, 6, 1, 0, 0), result.endDate());
        verify(seasonRepository, times(1)).findById("S123");
    }

    @Test
    void testFindById_whenSeasonDoesNotExist() {
        when(seasonRepository.findById(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> seasonService.findById("S123"));
        assertEquals("Season not found", exception.getMessage());
        verify(seasonRepository, times(1)).findById("S123");
    }

    @Test
    void testFindAll() {
        when(seasonRepository.findAll()).thenReturn(Collections.singletonList(season));

        List<SeasonResponseDTO> result = seasonService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("S123", result.get(0).id());
        assertEquals("Spring 2024", result.get(0).name());
        assertEquals(LocalDateTime.of(2024, 3, 1, 0, 0), result.get(0).startDate());
        assertEquals(LocalDateTime.of(2024, 6, 1, 0, 0), result.get(0).endDate());
        verify(seasonRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        doNothing().when(seasonRepository).deleteById(anyString());

        seasonService.delete("S123");

        verify(seasonRepository, times(1)).deleteById("S123");
    }

    @Test
    void testFindByName_whenSeasonExists() {
        when(seasonRepository.findByName(anyString())).thenReturn(Optional.of(season));

        Optional<Season> result = seasonService.findByName("Spring 2024");

        assertTrue(result.isPresent());
        assertEquals("S123", result.get().getId());
        assertEquals("Spring 2024", result.get().getName());
        assertEquals(LocalDateTime.of(2024, 3, 1, 0, 0), result.get().getStartDate());
        assertEquals(LocalDateTime.of(2024, 6, 1, 0, 0), result.get().getEndDate());
        verify(seasonRepository, times(1)).findByName("Spring 2024");
    }

    @Test
    void testFindByName_whenSeasonDoesNotExist() {
        when(seasonRepository.findByName(anyString())).thenReturn(Optional.empty());

        Optional<Season> result = seasonService.findByName("Nonexistent Season");

        assertFalse(result.isPresent());
        verify(seasonRepository, times(1)).findByName("Nonexistent Season");
    }
}
