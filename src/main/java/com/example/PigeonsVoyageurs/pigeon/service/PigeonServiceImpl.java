package com.example.PigeonsVoyageurs.pigeon.service;


import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import com.example.PigeonsVoyageurs.pigeon.PigeonMapper;
import com.example.PigeonsVoyageurs.pigeon.PigeonRepository;
import com.example.PigeonsVoyageurs.pigeon.dto.PigeonRequestDTO;
import com.example.PigeonsVoyageurs.pigeon.dto.PigeonResponseDTO;
import com.example.PigeonsVoyageurs.ranking.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PigeonServiceImpl implements PigeonService {

    private final PigeonRepository pigeonRepository;
    private final PigeonMapper pigeonMapper;

    public PigeonServiceImpl(PigeonRepository pigeonRepository, PigeonMapper pigeonMapper) {
        this.pigeonRepository = pigeonRepository;
        this.pigeonMapper = pigeonMapper;
    }


    @Override
    public PigeonResponseDTO save(PigeonRequestDTO pigeonRequestDTO) {
        Pigeon pigeon = pigeonMapper.toEntity(pigeonRequestDTO);
        pigeon = pigeonRepository.save(pigeon);
        return pigeonMapper.toResponseDTO(pigeon);
    }

    @Override
    public PigeonResponseDTO findById(String id) {
        return pigeonRepository.findById(id)
                .map(pigeonMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Pigeon not found"));
    }

    @Override
    public List<PigeonResponseDTO> findAll() {
        return pigeonRepository.findAll().stream()
                .map(pigeonMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        pigeonRepository.deleteById(id);
    }

}
