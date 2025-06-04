package com.jarmison.incassdev.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

import com.jarmison.incassdev.core.domain.enums.StatusProcesso;

@Entity
@Table(name = "processos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroProcesso;

    @Column(nullable = false)
    private String vara;

    @Column(nullable = false)
    private String comarca;

    @Column(nullable = false)
    private String assunto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProcesso status;

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Audiencia> audiencias;
}