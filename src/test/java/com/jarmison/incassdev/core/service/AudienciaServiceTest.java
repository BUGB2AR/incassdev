package com.jarmison.incassdev.core.service;

import com.jarmison.incassdev.core.domain.enums.StatusProcesso;
import com.jarmison.incassdev.core.domain.model.Audiencia;
import com.jarmison.incassdev.core.domain.model.Processo;
import com.jarmison.incassdev.core.infrastructure.repository.AudienciaRepository;
import com.jarmison.incassdev.core.infrastructure.repository.ProcessoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AudienciaServiceTest {

    @Autowired
    private AudienciaService audienciaService;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AudienciaRepository audienciaRepository;

    private Processo processoExistente;

    @BeforeEach
    public void setup() {

        Processo novoProcesso = new Processo();
        novoProcesso.setNumeroProcesso("0001234-56.2024.8.26.0100");
        novoProcesso.setVara("Vara Cível");
        novoProcesso.setComarca("São Paulo");
        novoProcesso.setAssunto("Cobrança indevida");
        novoProcesso.setStatus(StatusProcesso.ATIVO);

        processoExistente = processoRepository.save(novoProcesso);
    }

    @Test
    public void deveAgendarAudienciaComSucesso() {
        Audiencia audiencia = new Audiencia();
        audiencia.setDataHora(LocalDateTime.of(2025, 6, 10, 10, 0));
        audiencia.setProcesso(processoExistente);
        audiencia.setLocal("Fórum João Mendes");
        audiencia.setVara("Vara Cível");
        audiencia.setTipoAudiencia(com.jarmison.incassdev.core.domain.enums.TipoAudiencia.CONCILIACAO);

        Audiencia agendada = audienciaService.agendarAudiencia(audiencia);

        assertNotNull(agendada.getId());
        assertEquals(audiencia.getDataHora(), agendada.getDataHora());
        assertEquals(audiencia.getLocal(), agendada.getLocal());
        assertEquals(audiencia.getVara(), agendada.getVara());
        assertEquals(audiencia.getTipoAudiencia(), agendada.getTipoAudiencia());
        assertEquals(processoExistente.getId(), agendada.getProcesso().getId());
    }

}
