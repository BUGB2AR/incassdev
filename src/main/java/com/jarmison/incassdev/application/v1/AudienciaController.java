package com.jarmison.incassdev.application.v1;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jarmison.incassdev.core.base.BaseController;
import com.jarmison.incassdev.core.domain.model.Audiencia;
import com.jarmison.incassdev.core.infrastructure.dto.AudienciaRequestDTO;
import com.jarmison.incassdev.core.infrastructure.dto.AudienciaResponseDTO;
import com.jarmison.incassdev.core.mapper.AudienciaMapper;
import com.jarmison.incassdev.core.service.AudienciaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audiencias")
public class AudienciaController extends BaseController<Audiencia, Long, AudienciaRequestDTO, AudienciaResponseDTO, AudienciaService, AudienciaMapper> {

    public AudienciaController(AudienciaService service, AudienciaMapper mapper) {
        super(service, mapper);
    }

    @Override
    public ResponseEntity<AudienciaResponseDTO> create(@Valid @RequestBody AudienciaRequestDTO requestDTO) {
        Audiencia audiencia = mapper.toEntity(requestDTO);
        Audiencia novaAudiencia = service.agendarAudiencia(audiencia); 
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(novaAudiencia));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AudienciaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AudienciaRequestDTO requestDTO) {
        Audiencia audienciaAtualizada = mapper.toEntity(requestDTO);
        Audiencia audienciaSalva = service.atualizarAudiencia(id, audienciaAtualizada); 
        return ResponseEntity.ok(mapper.toDto(audienciaSalva));
    }

    @GetMapping("/agenda")
    public ResponseEntity<List<AudienciaResponseDTO>> consultarAgenda(
            @RequestParam String comarca,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Audiencia> audiencias = service.consultarAgenda(comarca, data);
        List<AudienciaResponseDTO> dtos = audiencias.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}