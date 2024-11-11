package com.example.PigeonsVoyageurs.breeder;

import com.example.PigeonsVoyageurs.breeder.dto.BreederRequestDTO;
import com.example.PigeonsVoyageurs.breeder.dto.BreederResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BreederMapper {
    BreederMapper INSTANCE = Mappers.getMapper(BreederMapper.class);

    BreederRequestDTO toRequestDTO(Breeder breeder);

    BreederResponseDTO toResponseDTO(Breeder breeder);

    Breeder toEntity(BreederRequestDTO breederDTO);
}
