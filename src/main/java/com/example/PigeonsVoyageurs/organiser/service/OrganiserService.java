package com.example.PigeonsVoyageurs.organiser.service;

import com.example.PigeonsVoyageurs.organiser.OrganiserDTO;

import java.util.List;
import java.util.Optional;

public interface OrganiserService {
    Optional<OrganiserDTO> get(long id);
    List<OrganiserDTO> getAll();
    Optional<OrganiserDTO> save(OrganiserDTO organiserDTO);
    Optional<OrganiserDTO> update(OrganiserDTO organiserDTO);
    boolean delete(long id);
}
