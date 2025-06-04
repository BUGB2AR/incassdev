package com.jarmison.incassdev.core.infrastructure.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import com.jarmison.incassdev.core.domain.enums.TipoAudiencia;

@Data
public class AudienciaRequestDTO {
    @NotNull(message = "ID do processo é obrigatório")
    private Long processoId;

    @NotNull(message = "Data e hora da audiência são obrigatórias")
    @FutureOrPresent(message = "A data e hora da audiência devem ser no presente ou futuro")
    private LocalDateTime dataHora;

    @NotNull(message = "Tipo de audiência é obrigatório")
    private TipoAudiencia tipoAudiencia;

    @NotBlank(message = "Local da audiência é obrigatório")
    private String local;

    @NotBlank(message = "Vara da audiência é obrigatória")
    private String vara;
}