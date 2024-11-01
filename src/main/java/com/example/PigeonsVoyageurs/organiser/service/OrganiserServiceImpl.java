package com.example.PigeonsVoyageurs.organiser.service;

import com.example.PigeonsVoyageurs.organiser.Organiser;
import com.example.PigeonsVoyageurs.organiser.OrganiserDTO;
import com.example.PigeonsVoyageurs.organiser.OrganiserRepository;
import com.example.PigeonsVoyageurs.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrganiserServiceImpl implements OrganiserService{
    @Autowired
    private OrganiserRepository organiserRepository;

    @Autowired
    private Mapper<Organiser, OrganiserDTO> organiserMapper;

    @Override
    public Optional<OrganiserDTO> get(long id) {
        Optional<Organiser> organiserOptional = organiserRepository.findById(id);

        if (organiserOptional.isPresent()) {
            Organiser organiser = organiserOptional.get();
            OrganiserDTO organiserDTO = organiserMapper.toDTO(organiser);
            return Optional.of(organiserDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<OrganiserDTO> getAll() {
        return organiserMapper.toDTOList(organiserRepository.findAll());
    }

    @Override
    public Optional<OrganiserDTO> save(OrganiserDTO organiserDTO) {
        Organiser organiser = organiserMapper.fromDTO(organiserDTO);
        return Optional.of(organiserMapper.toDTO(organiserRepository.insert(organiser)));
    }

    @Override
    public Optional<OrganiserDTO> update(OrganiserDTO organiserDTO) {
        Organiser organiser = organiserMapper.fromDTO(organiserDTO);
        return Optional.of(organiserMapper.toDTO(organiserRepository.save(organiser)));
    }

    @Override
    public boolean delete(long id) {
        Organiser existingOrganiser = organiserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No organiser corresponding to this identity."));

        organiserRepository.delete(existingOrganiser);

        return !organiserRepository.existsById(id);
    }
}
