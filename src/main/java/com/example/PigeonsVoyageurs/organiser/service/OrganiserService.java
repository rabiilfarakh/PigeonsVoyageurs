package com.example.PigeonsVoyageurs.organiser.service;

import com.example.PigeonsVoyageurs.organiser.Organiser;
import com.example.PigeonsVoyageurs.organiser.OrganiserDTO;

import java.util.List;
import java.util.Optional;

public interface OrganiserService {

    Organiser register(OrganiserDTO organiserDTO);

    Optional<String> login(String userName, String password);

    Optional<Organiser> findById(Long id);

    Optional<Organiser> findByEmail(String email);
    List<Organiser> findAll();

    Organiser update(Long id, OrganiserDTO organiserDTO);

    void delete(Long id);
}
