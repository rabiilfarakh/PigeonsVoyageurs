package com.example.PigeonsVoyageurs.loft;

import com.example.PigeonsVoyageurs.loft.dto.LoftRequestDTO;
import com.example.PigeonsVoyageurs.loft.dto.LoftResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoftMapper {
    LoftMapper INSTANCE = Mappers.getMapper(LoftMapper.class);

    LoftRequestDTO toRequestDTO(Loft loft);

    LoftResponseDTO toResponseDTO(Loft loft);

    Loft toEntity(LoftRequestDTO loftDTO);
}
