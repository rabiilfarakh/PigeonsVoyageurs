package com.example.PigeonsVoyageurs.organiser.service;

import com.example.PigeonsVoyageurs.organiser.Organiser;
import com.example.PigeonsVoyageurs.organiser.OrganiserDTO;

import java.util.List;
import java.util.Optional;

public interface OrganiserService {

    Organiser register(OrganiserDTO organiserDTO);

    Optional<String> login(String userName, String password);

    Optional<Organiser> findById(String id);

    Optional<Organiser> findByEmail(String email);

    List<Organiser> findAll();

    Organiser update(String id, OrganiserDTO organiserDTO);

    void delete(String id);
}
