package com.jarmison.incassdev.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.jarmison.incassdev.core.base.BaseMapper;
import com.jarmison.incassdev.core.domain.model.Processo;
import com.jarmison.incassdev.core.infrastructure.dto.ProcessoRequestDTO;
import com.jarmison.incassdev.core.infrastructure.dto.ProcessoResponseDTO;

@Mapper(componentModel = "spring")
public interface ProcessoMapper extends BaseMapper<Processo, ProcessoRequestDTO, ProcessoResponseDTO> {

    ProcessoMapper INSTANCE = Mappers.getMapper(ProcessoMapper.class);

    @Override
    Processo toEntity(ProcessoRequestDTO dto);

    @Override
    ProcessoResponseDTO toDto(Processo entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ProcessoRequestDTO dto, @MappingTarget Processo entity);
}
