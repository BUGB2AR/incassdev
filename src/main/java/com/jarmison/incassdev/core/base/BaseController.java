package com.jarmison.incassdev.core.base;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jarmison.incassdev.application.controller.BaseRoutesController;



public abstract class BaseController<E, ID, ReqDTO, ResDTO, S extends BaseService<E, ID, ?>, M extends BaseMapper<E, ReqDTO, ResDTO>>
        implements BaseRoutesController<ReqDTO, ResDTO, ID> {

    protected final S service;
    protected final M mapper;

    protected BaseController(S service, M mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ResDTO> create(@Valid @RequestBody ReqDTO requestDTO) {
        E entity = mapper.toEntity(requestDTO);
        E savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedEntity));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResDTO> getById(@PathVariable ID id) {
        E entity = service.findByIdOrThrow(id);
        return ResponseEntity.ok(mapper.toDto(entity));
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.deleteById(id); 
        return ResponseEntity.noContent().build();
    }
}