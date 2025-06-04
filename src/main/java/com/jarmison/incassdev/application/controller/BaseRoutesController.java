package com.jarmison.incassdev.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseRoutesController<ReqDTO, ResDTO, ID> {

    @PostMapping
    ResponseEntity<ResDTO> create(@RequestBody ReqDTO requestDTO);

    @GetMapping("/{id}")
    ResponseEntity<ResDTO> getById(@PathVariable ID id);


    @PutMapping("/{id}")
    ResponseEntity<ResDTO> update(@PathVariable ID id, @RequestBody ReqDTO requestDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable ID id);
}