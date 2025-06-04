package com.jarmison.incassdev.core.infrastructure.dto;

import com.jarmison.incassdev.core.domain.enums.StatusProcesso;
import com.jarmison.incassdev.core.domain.validation.NumeroProcesso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcessoRequestDTO {
    @NotBlank(message = "Número do processo é obrigatório")
    @NumeroProcesso
    private String numeroProcesso;

    @NotBlank(message = "Vara é obrigatória")
    private String vara;

    @NotBlank(message = "Comarca é obrigatória")
    private String comarca;

    @NotBlank(message = "Assunto é obrigatório")
    private String assunto;

    @NotNull(message = "Status é obrigatório")
    private StatusProcesso status;
}
