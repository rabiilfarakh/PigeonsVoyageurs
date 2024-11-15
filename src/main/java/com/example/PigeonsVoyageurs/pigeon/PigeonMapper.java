package com.example.PigeonsVoyageurs.pigeon;

import com.example.PigeonsVoyageurs.pigeon.dto.PigeonRequestDTO;
import com.example.PigeonsVoyageurs.pigeon.dto.PigeonResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PigeonMapper {

    @Mapping(target = "rankings", ignore = true)
    Pigeon toEntity(PigeonRequestDTO dto);
    PigeonResponseDTO toResponseDTO(Pigeon pigeon);
}