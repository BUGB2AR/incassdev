package com.jarmison.incassdev.core.infrastructure.dto;

import com.jarmison.incassdev.core.domain.enums.StatusProcesso;

import lombok.Data;

@Data
public class ProcessoResponseDTO {
    private Long id;
    private String numeroProcesso;
    private String vara;
    private String comarca;
    private String assunto;
    private StatusProcesso status;
}