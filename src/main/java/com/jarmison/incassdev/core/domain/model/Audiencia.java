package com.jarmison.incassdev.core.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import com.jarmison.incassdev.core.domain.enums.TipoAudiencia;

@Entity
@Table(name = "audiencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAudiencia tipoAudiencia;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private String vara;
}