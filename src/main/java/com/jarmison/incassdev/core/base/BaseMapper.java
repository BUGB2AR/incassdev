package com.jarmison.incassdev.core.base;

public interface BaseMapper<E, ReqDTO, ResDTO> {
    E toEntity(ReqDTO dto);
    ResDTO toDto(E entity);
}
