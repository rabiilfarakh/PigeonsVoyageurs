package com.example.PigeonsVoyageurs.pigeon.dto;

import com.example.PigeonsVoyageurs.enumeration.Sexe;

public record PigeonRequestDTO(String pigeonRing, Sexe sexe, String color, int age) {}