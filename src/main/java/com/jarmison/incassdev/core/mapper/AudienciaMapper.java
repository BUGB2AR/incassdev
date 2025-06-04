package com.jarmison.incassdev.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.jarmison.incassdev.core.base.BaseMapper;
import com.jarmison.incassdev.core.domain.model.Audiencia;
import com.jarmison.incassdev.core.infrastructure.dto.AudienciaRequestDTO;
import com.jarmison.incassdev.core.infrastructure.dto.AudienciaResponseDTO;

@Mapper
public interface AudienciaMapper extends BaseMapper<Audiencia, AudienciaRequestDTO, AudienciaResponseDTO> {

    AudienciaMapper INSTANCE = Mappers.getMapper(AudienciaMapper.class);

    @Mapping(target = "processo.id", source = "processoId")
    @Override
    Audiencia toEntity(AudienciaRequestDTO dto);

    @Mapping(target = "processoId", source = "processo.id")
    @Mapping(target = "numeroProcesso", source = "processo.numeroProcesso")
    @Override
    AudienciaResponseDTO toDto(Audiencia entity);
}