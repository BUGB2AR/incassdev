package com.jarmison.incassdev.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jarmison.incassdev.core.base.BaseService;
import com.jarmison.incassdev.core.domain.enums.StatusProcesso;
import com.jarmison.incassdev.core.domain.exception.BusinessException;
import com.jarmison.incassdev.core.domain.exception.ResourceNotFoundException;
import com.jarmison.incassdev.core.domain.model.Processo;
import com.jarmison.incassdev.core.infrastructure.repository.ProcessoRepository;

import java.util.List;

@Service
public class ProcessoService extends BaseService<Processo, Long, ProcessoRepository> {

    public ProcessoService(ProcessoRepository repository) {
        super(repository);
    }

    @Transactional
    public Processo criarProcesso(Processo processo) {
        if (repository.findByNumeroProcesso(processo.getNumeroProcesso()).isPresent()) {
            throw new BusinessException("Já existe um processo com o número: " + processo.getNumeroProcesso());
        }
        return repository.save(processo);
    }

    @Transactional
    public Processo atualizarProcesso(Long id, Processo processoAtualizado) {
        Processo processoExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Processo com ID " + id + " não encontrado."));

        if (!processoExistente.getNumeroProcesso().equals(processoAtualizado.getNumeroProcesso())) {
            if (repository.findByNumeroProcesso(processoAtualizado.getNumeroProcesso()).isPresent()) {
                throw new BusinessException("Não é possível alterar para o número de processo " + processoAtualizado.getNumeroProcesso() + " pois ele já existe.");
            }
        }

        processoExistente.setNumeroProcesso(processoAtualizado.getNumeroProcesso());
        processoExistente.setVara(processoAtualizado.getVara());
        processoExistente.setComarca(processoAtualizado.getComarca());
        processoExistente.setAssunto(processoAtualizado.getAssunto());
        processoExistente.setStatus(processoAtualizado.getStatus());

        return repository.save(processoExistente);
    }

    public List<Processo> listarProcessos(StatusProcesso status, String comarca) {
        if (status != null && comarca != null && !comarca.isEmpty()) {
            return repository.findByStatusAndComarca(status, comarca);
        } else if (status != null) {
            return repository.findByStatus(status);
        } else if (comarca != null && !comarca.isEmpty()) {
            return repository.findByComarca(comarca);
        } else {
            return repository.findAll();
        }
    }
}
