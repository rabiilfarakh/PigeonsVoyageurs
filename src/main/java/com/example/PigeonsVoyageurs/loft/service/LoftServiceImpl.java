package com.example.PigeonsVoyageurs.loft.service;

import com.example.PigeonsVoyageurs.loft.Loft;
import com.example.PigeonsVoyageurs.loft.LoftMapper;
import com.example.PigeonsVoyageurs.loft.LoftRepository;
import com.example.PigeonsVoyageurs.loft.dto.LoftRequestDTO;
import com.example.PigeonsVoyageurs.loft.dto.LoftResponseDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoftServiceImpl implements LoftService {

    private final LoftRepository loftRepository;
    private final LoftMapper loftMapper;
    
    @Autowired
    public LoftServiceImpl(LoftRepository loftRepository, LoftMapper loftMapper) {
        this.loftRepository = loftRepository;
        this.loftMapper = loftMapper;
    }

    @Override
    public LoftResponseDTO save(LoftRequestDTO loftDTO) {
        Loft loft = loftMapper.toEntity(loftDTO);
        loft = loftRepository.save(loft);
        return loftMapper.toResponseDTO(loft);
    }

    @Override
    public Optional<LoftResponseDTO> findById(String id) {
        return loftRepository.findById(id)
                .map(loftMapper::toResponseDTO);
    }

    @Override
    public Optional<LoftResponseDTO> findByName(String name) {
        return loftRepository.findByName(name)
                .map(loftMapper::toResponseDTO);
    }

    @Override
    public List<LoftResponseDTO> findAll() {
        return loftRepository.findAll().stream()
                .map(loftMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoftResponseDTO update(String id, LoftRequestDTO loftDTO) {
        Loft loft = loftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loft not found"));

        loft.setName(loftDTO.name());
        loft.setCoordinate(loftDTO.coordinate());

        Loft updatedLoft = loftRepository.save(loft);

        return loftMapper.toResponseDTO(updatedLoft);
    }

    @Override
    public void delete(String id) {
        loftRepository.deleteById(id);
    }
}
