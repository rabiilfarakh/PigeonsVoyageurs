package com.example.PigeonsVoyageurs.breeder.dto;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;

public record BreederRequestDTO(String userName, String password, String email, Coordinate coordinate) {
}
