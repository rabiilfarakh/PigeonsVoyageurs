package com.example.PigeonsVoyageurs.organiser;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganiserMapper {
    OrganiserMapper INSTANCE = Mappers.getMapper(OrganiserMapper.class);

    OrganiserDTO toDTO(Organiser organiser);
    Organiser toEntity(OrganiserDTO organiserDTO);
}
