package com.example.PigeonsVoyageurs.pigeon.service;

import com.example.PigeonsVoyageurs.pigeon.dto.PigeonRequestDTO;
import com.example.PigeonsVoyageurs.pigeon.dto.PigeonResponseDTO;

import java.util.List;

public interface PigeonService {

    PigeonResponseDTO save(PigeonRequestDTO pigeonRequestDTO);

    PigeonResponseDTO findById(String pigeonRing);

    List<PigeonResponseDTO> findAll();

    void delete(String pigeonRing);
}

