package com.jarmison.incassdev.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jarmison.incassdev.core.domain.model.Audiencia;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AudienciaRepository extends JpaRepository<Audiencia, Long> {
    boolean existsByVaraAndLocalAndDataHoraBetween(String vara, String local, LocalDateTime start, LocalDateTime end);
    List<Audiencia> findByComarcaAndDataHoraBetween(String comarca, LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Audiencia> findByProcessoComarcaAndDataHoraBetween(String comarca, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
