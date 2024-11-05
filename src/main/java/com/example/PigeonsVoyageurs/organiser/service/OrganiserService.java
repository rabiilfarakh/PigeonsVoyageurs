package com.example.PigeonsVoyageurs.organiser.service;

import com.example.PigeonsVoyageurs.organiser.Organiser;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserRequestDTO;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface OrganiserService {

    OrganiserResponseDTO register(OrganiserRequestDTO organiserDTO);

    Optional<String> login(String userName, String password);

    Optional<OrganiserResponseDTO> findById(String id);

    Optional<OrganiserResponseDTO> findByEmail(String email);

    List<OrganiserResponseDTO> findAll();

    OrganiserResponseDTO update(String id, OrganiserRequestDTO organiserDTO);

    void delete(String id);
}
