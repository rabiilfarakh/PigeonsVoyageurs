package com.example.PigeonsVoyageurs.pigeon;

import com.example.PigeonsVoyageurs.enumeration.Sexe;
import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import com.example.PigeonsVoyageurs.pigeon.PigeonMapper;
import com.example.PigeonsVoyageurs.pigeon.PigeonRepository;
import com.example.PigeonsVoyageurs.pigeon.dto.PigeonRequestDTO;
import com.example.PigeonsVoyageurs.pigeon.dto.PigeonResponseDTO;
import com.example.PigeonsVoyageurs.pigeon.service.PigeonServiceImpl;
import com.example.PigeonsVoyageurs.ranking.Ranking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PigeonServiceImplTest {

    @Mock
    private PigeonRepository pigeonRepository;

    @Mock
    private PigeonMapper pigeonMapper;

    @InjectMocks
    private PigeonServiceImpl pigeonService;

    private Pigeon pigeon;
    private PigeonRequestDTO pigeonRequestDTO;
    private PigeonResponseDTO pigeonResponseDTO;

    @BeforeEach
    void setUp() {
        List<Ranking> rankings = Collections.emptyList();

        pigeon = new Pigeon();
        pigeon.setPigeonRing("R123");
        pigeon.setSexe(Sexe.MALE);
        pigeon.setColor("Blue");
        pigeon.setAge(3);
        pigeon.setRankings(rankings);

        pigeonRequestDTO = new PigeonRequestDTO("R123", Sexe.MALE, "Blue", 3);
        pigeonResponseDTO = new PigeonResponseDTO("R123", Sexe.MALE, "Blue", 3, rankings);

        lenient().when(pigeonMapper.toEntity(any(PigeonRequestDTO.class))).thenReturn(pigeon);
        lenient().when(pigeonMapper.toResponseDTO(any(Pigeon.class))).thenReturn(pigeonResponseDTO);
    }

    @Test
    void testSave() {
        when(pigeonRepository.save(any(Pigeon.class))).thenReturn(pigeon);

        PigeonResponseDTO result = pigeonService.save(pigeonRequestDTO);

        assertNotNull(result);
        assertEquals("R123", result.pigeonRing());
        assertEquals(Sexe.MALE, result.sexe());
        assertEquals("Blue", result.color());
        assertEquals(3, result.age());
        assertEquals(Collections.emptyList(), result.rankings());
        verify(pigeonRepository, times(1)).save(any(Pigeon.class));
        verify(pigeonMapper, times(1)).toEntity(pigeonRequestDTO);
        verify(pigeonMapper, times(1)).toResponseDTO(pigeon);
    }

    @Test
    void testFindById_whenPigeonExists() {
        when(pigeonRepository.findById(anyString())).thenReturn(Optional.of(pigeon));

        PigeonResponseDTO result = pigeonService.findById("R123");

        assertNotNull(result);
        assertEquals("R123", result.pigeonRing());
        assertEquals(Sexe.MALE, result.sexe());
        assertEquals("Blue", result.color());
        assertEquals(3, result.age());
        verify(pigeonRepository, times(1)).findById("R123");
        verify(pigeonMapper, times(1)).toResponseDTO(pigeon);
    }

    @Test
    void testFindById_whenPigeonDoesNotExist() {
        when(pigeonRepository.findById(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> pigeonService.findById("R123"));
        assertEquals("Pigeon not found", exception.getMessage());
        verify(pigeonRepository, times(1)).findById("R123");
        verify(pigeonMapper, never()).toResponseDTO(any(Pigeon.class));
    }

    @Test
    void testFindAll() {
        when(pigeonRepository.findAll()).thenReturn(Collections.singletonList(pigeon));

        List<PigeonResponseDTO> result = pigeonService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("R123", result.get(0).pigeonRing());
        assertEquals(Sexe.MALE, result.get(0).sexe());
        assertEquals("Blue", result.get(0).color());
        assertEquals(3, result.get(0).age());
        verify(pigeonRepository, times(1)).findAll();
        verify(pigeonMapper, times(1)).toResponseDTO(pigeon);
    }

    @Test
    void testDelete() {
        doNothing().when(pigeonRepository).deleteById(anyString());

        pigeonService.delete("R123");

        verify(pigeonRepository, times(1)).deleteById("R123");
    }
}
