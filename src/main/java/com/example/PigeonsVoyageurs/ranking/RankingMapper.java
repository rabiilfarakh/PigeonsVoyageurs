package com.example.PigeonsVoyageurs.ranking;

import com.example.PigeonsVoyageurs.ranking.dto.RankingRequestDTO;
import com.example.PigeonsVoyageurs.ranking.dto.RankingResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RankingMapper {

    @Mapping(target = "competition.id", source = "competitionId")
    @Mapping(target = "pigeon.pigeonRing", source = "pigeonId")
    Ranking toEntity(RankingRequestDTO dto);

    @Mapping(target = "competitionName", source = "competition.raceName")
    @Mapping(target = "pigeonRing", source = "pigeon.pigeonRing")
    RankingResponseDTO toDTO(Ranking ranking);
}

