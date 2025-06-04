package com.jarmison.incassdev.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jarmison.incassdev.core.base.BaseService;
import com.jarmison.incassdev.core.domain.enums.StatusProcesso;
import com.jarmison.incassdev.core.domain.exception.BusinessException;
import com.jarmison.incassdev.core.domain.model.Audiencia;
import com.jarmison.incassdev.core.domain.model.Processo;
import com.jarmison.incassdev.core.infrastructure.repository.AudienciaRepository;
import com.jarmison.incassdev.core.infrastructure.repository.ProcessoRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AudienciaService extends BaseService<Audiencia, Long, AudienciaRepository> {

    private final ProcessoRepository processoRepository;

    public AudienciaService(AudienciaRepository repository, ProcessoRepository processoRepository) {
        super(repository);
        this.processoRepository = processoRepository;
    }

    @Transactional
    public Audiencia agendarAudiencia(Audiencia audiencia) {
        Processo processo = processoRepository.findById(audiencia.getProcesso().getId())
                .orElseThrow(() -> new BusinessException("Processo não encontrado para o agendamento da audiência."));

        if (processo.getStatus() == StatusProcesso.ARQUIVADO || processo.getStatus() == StatusProcesso.SUSPENSO) {
            throw new BusinessException("Não é possível agendar audiência para processo arquivado ou suspenso.");
        }

        if (audiencia.getDataHora().getDayOfWeek() == DayOfWeek.SATURDAY || audiencia.getDataHora().getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new BusinessException("Audiência só pode ser marcada em dias úteis (segunda a sexta-feira).");
        }


        LocalDateTime inicioSlot = audiencia.getDataHora().minusMinutes(1);
        LocalDateTime fimSlot = audiencia.getDataHora().plusMinutes(1);

        if (repository.existsByVaraAndLocalAndDataHoraBetween(audiencia.getVara(), audiencia.getLocal(), inicioSlot, fimSlot)) {
            throw new BusinessException("Já existe uma audiência agendada para a mesma vara e local neste horário.");
        }

        audiencia.setProcesso(processo); 
        return repository.save(audiencia);
    }

    public List<Audiencia> consultarAgenda(String comarca, LocalDate data) {
        LocalDateTime startOfDay = data.atStartOfDay();
        LocalDateTime endOfDay = data.atTime(LocalTime.MAX);
        return repository.findByProcessoComarcaAndDataHoraBetween(comarca, startOfDay, endOfDay);
    }

    @Transactional
    public Audiencia atualizarAudiencia(Long id, Audiencia audienciaAtualizada) {
        Audiencia audienciaExistente = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Audiência com ID " + id + " não encontrada."));

        Processo processo = processoRepository.findById(audienciaAtualizada.getProcesso().getId())
                .orElseThrow(() -> new BusinessException("Processo não encontrado para a audiência."));

        if (processo.getStatus() == StatusProcesso.ARQUIVADO || processo.getStatus() == StatusProcesso.SUSPENSO) {
            throw new BusinessException("Não é possível agendar audiência para processo arquivado ou suspenso.");
        }

        if (audienciaAtualizada.getDataHora().getDayOfWeek() == DayOfWeek.SATURDAY || audienciaAtualizada.getDataHora().getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new BusinessException("Audiência só pode ser marcada em dias úteis (segunda a sexta-feira).");
        }

        LocalDateTime inicioSlot = audienciaAtualizada.getDataHora().minusMinutes(1);
        LocalDateTime fimSlot = audienciaAtualizada.getDataHora().plusMinutes(1);

        boolean hasOverlap = repository.findAll().stream()
                .filter(a -> !a.getId().equals(id))
                .anyMatch(a -> a.getVara().equals(audienciaAtualizada.getVara()) &&
                               a.getLocal().equals(audienciaAtualizada.getLocal()) &&
                               a.getDataHora().isAfter(inicioSlot) && a.getDataHora().isBefore(fimSlot));

        if (hasOverlap) {
            throw new BusinessException("Já existe uma audiência agendada para a mesma vara e local neste horário.");
        }

        audienciaExistente.setDataHora(audienciaAtualizada.getDataHora());
        audienciaExistente.setTipoAudiencia(audienciaAtualizada.getTipoAudiencia());
        audienciaExistente.setLocal(audienciaAtualizada.getLocal());
        audienciaExistente.setVara(audienciaAtualizada.getVara());
        audienciaExistente.setProcesso(processo);

        return repository.save(audienciaExistente);
    }
}