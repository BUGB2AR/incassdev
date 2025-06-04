package com.jarmison.incassdev.core.base;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jarmison.incassdev.core.domain.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<E, ID, R extends JpaRepository<E, ID>> {

    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    public E findByIdOrThrow(ID id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso com ID " + id + " não encontrado."));
    }

    public E save(E entity) {
        return repository.save(entity);
    }

    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso com ID " + id + " não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }
}
