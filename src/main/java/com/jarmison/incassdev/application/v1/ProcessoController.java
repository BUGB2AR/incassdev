package com.jarmison.incassdev.application.v1;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jarmison.incassdev.core.base.BaseController;
import com.jarmison.incassdev.core.domain.enums.StatusProcesso;
import com.jarmison.incassdev.core.domain.model.Processo;
import com.jarmison.incassdev.core.infrastructure.dto.ProcessoRequestDTO;
import com.jarmison.incassdev.core.infrastructure.dto.ProcessoResponseDTO;
import com.jarmison.incassdev.core.mapper.ProcessoMapper;
import com.jarmison.incassdev.core.service.ProcessoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/processos")
public class ProcessoController extends BaseController<Processo, Long, ProcessoRequestDTO, ProcessoResponseDTO, ProcessoService, ProcessoMapper> {

    public ProcessoController(ProcessoService service, ProcessoMapper mapper) {
        super(service, mapper);
    }
    

    @Override
    public ResponseEntity<ProcessoResponseDTO> create(@Valid @RequestBody ProcessoRequestDTO requestDTO) {
        Processo processo = mapper.toEntity(requestDTO);
        Processo novoProcesso = service.criarProcesso(processo);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(novoProcesso));
    }

    @Override
    public ResponseEntity<ProcessoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProcessoRequestDTO requestDTO) {
        Processo processoAtualizado = mapper.toEntity(requestDTO);
        Processo processoSalvo = service.atualizarProcesso(id, processoAtualizado);
        return ResponseEntity.ok(mapper.toDto(processoSalvo));
    }


    @GetMapping("/todos-processos")
    public ResponseEntity<List<ProcessoResponseDTO>> listarProcessos(
            @RequestParam(required = false) StatusProcesso status,
            @RequestParam(required = false) String comarca) {
        List<Processo> processos = service.listarProcessos(status, comarca);
        List<ProcessoResponseDTO> dtos = processos.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    
}