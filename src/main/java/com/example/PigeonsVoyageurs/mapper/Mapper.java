package com.example.PigeonsVoyageurs.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper<Entity, EntityDTO> {
    @Autowired
    private ModelMapper modelMapper;

    private final Class<Entity> entityClass;
    private final Class<EntityDTO> entityDTOClass;

    public Mapper(Class<Entity> entityClass, Class<EntityDTO> entityDTOClass) {
        this.entityClass = entityClass;
        this.entityDTOClass = entityDTOClass;
    }

    public EntityDTO toDTO(Entity entity) {
        return modelMapper.map(entity, entityDTOClass);
    }

    public Entity fromDTO(EntityDTO entityDTO) {
        return modelMapper.map(entityDTO, entityClass);
    }

    public List<EntityDTO> toDTOList(List<Entity> entityList) {
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Entity> fromDTOList(List<EntityDTO> entityDTOList) {
        return entityDTOList.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
