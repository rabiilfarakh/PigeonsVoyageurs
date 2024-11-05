package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.organiser.dto.OrganiserRequestDTO;
import com.example.PigeonsVoyageurs.organiser.dto.OrganiserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganiserMapper {
    OrganiserMapper INSTANCE = Mappers.getMapper(OrganiserMapper.class);

    OrganiserRequestDTO toRequestDTO(Organiser organiser);

    OrganiserResponseDTO toResponseDTO(Organiser organiser);

    Organiser toEntity(OrganiserRequestDTO organiserDTO);
}
