package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.organiser.dto.OrganiserRequestDTO;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganiserMapper {
    OrganiserResponseDTO toResponseDTO(Organiser organiser);
    Organiser toEntity(OrganiserRequestDTO organiserDTO);
}
