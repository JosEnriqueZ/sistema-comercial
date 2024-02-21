package com.elolympus.services;

import com.elolympus.data.Administracion.Persona;
import com.elolympus.data.Administracion.PersonaRepository;

import com.elolympus.data.SamplePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository repository;
    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }
    public Page<Persona> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public int count() {
        return (int) repository.count();
    }
    public Persona update(Persona entity) {
        return repository.save(entity);
    }

    public Optional<Persona> get(Long id) {
        return repository.findById(id);
    }
}
