package com.example.PigeonsVoyageurs.breeder.service;

import com.example.PigeonsVoyageurs.breeder.Breeder;
import com.example.PigeonsVoyageurs.breeder.BreederDTO;
import com.example.PigeonsVoyageurs.breeder.BreederRepository;
import com.example.PigeonsVoyageurs.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreederServiceImpl implements BreederService{
    @Autowired
    private BreederRepository breederRepository;

    private final Mapper<Breeder, BreederDTO> breederMapper;

    public BreederServiceImpl(Mapper<Breeder, BreederDTO> breederMapper) {
        this.breederMapper = breederMapper;
    }

    @Override
    public Optional<BreederDTO> get(long id) {
        Optional<Breeder> breederOptional = breederRepository.findById(id);

        if (breederOptional.isPresent()) {
            Breeder breeder = breederOptional.get();
            BreederDTO breederDTO = breederMapper.toDTO(breeder);
            return Optional.of(breederDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<BreederDTO> getAll() {
        return breederMapper.toDTOList(breederRepository.findAll());
    }

    @Override
    public BreederDTO save(BreederDTO breederDTO) {
        Breeder breeder = breederMapper.fromDTO(breederDTO);
        return breederMapper.toDTO(breederRepository.insert(breeder));
    }

    @Override
    public BreederDTO update(BreederDTO breederDTO) {
        Breeder breeder = breederMapper.fromDTO(breederDTO);
        return breederMapper.toDTO(breederRepository.save(breeder));
    }

    @Override
    public boolean delete(long id) {
        Breeder existingBreeder = breederRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No breeder corresponding to this identity."));

        breederRepository.delete(existingBreeder);

        return !breederRepository.existsById(id);
    }
}
