package com.example.PigeonsVoyageurs.breeder.service;

import com.example.PigeonsVoyageurs.breeder.BreederDTO;

import java.util.List;
import java.util.Optional;

public interface BreederService {
    Optional<BreederDTO> get(long id);
    List<BreederDTO> getAll();
    Optional<BreederDTO> save(BreederDTO breederDTO);
    Optional<BreederDTO> update(BreederDTO breederDTO);
    boolean delete(long id);
}
