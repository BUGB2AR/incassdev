package com.jarmison.incassdev.core.infrastructure.dto;

import java.time.LocalDateTime;

import com.jarmison.incassdev.core.domain.enums.TipoAudiencia;

import lombok.Data;

@Data
public class AudienciaResponseDTO {
	private Long id;
	private Long processoId;
	private String numeroProcesso; 
	private LocalDateTime dataHora;
	private TipoAudiencia tipoAudiencia;
	private String local;
	private String vara;
}
