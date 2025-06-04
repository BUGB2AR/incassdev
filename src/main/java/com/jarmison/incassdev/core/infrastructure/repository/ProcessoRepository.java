package com.jarmison.incassdev.core.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jarmison.incassdev.core.domain.enums.StatusProcesso;
import com.jarmison.incassdev.core.domain.model.Processo;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    Optional<Processo> findByNumeroProcesso(String numeroProcesso);
    List<Processo> findByStatus(StatusProcesso status);
    List<Processo> findByComarca(String comarca);
    List<Processo> findByStatusAndComarca(StatusProcesso status, String comarca);
}