package com.example.PigeonsVoyageurs.season;

import com.example.PigeonsVoyageurs.season.dto.SeasonRequestDTO;
import com.example.PigeonsVoyageurs.season.dto.SeasonResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    SeasonResponseDTO toResponseDTO(Season season);

    Season toEntity(SeasonRequestDTO seasonDTO);
}
