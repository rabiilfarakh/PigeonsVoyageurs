package com.example.PigeonsVoyageurs.competition;

import com.example.PigeonsVoyageurs.competition.dto.CompetitionRequestDTO;
import com.example.PigeonsVoyageurs.competition.dto.CompetitionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    CompetitionResponseDTO toResponseDTO(Competition competition);
    @Mapping(target = "season.id", source = "seasonId")
    Competition toEntity(CompetitionRequestDTO competitionDTO);
}
