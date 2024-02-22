package com.elolympus.services;

import com.elolympus.data.Administracion.Persona;
import com.elolympus.data.Administracion.PersonaRepository;

import com.elolympus.data.Administracion.PersonaSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<Persona> list(Pageable pageable, Specification<Persona> filter) {
        return repository.findAll(filter,pageable);
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

    public List<Persona> buscarPorNombresYApellidos(String nombres, String apellidos) {
        return repository.findByNombresContainingAndApellidosContaining(nombres, apellidos);
    }

    public List<Persona> buscarPorNombresYApellidosActivos(String nombres, String apellidos) {
        return repository.findAll(PersonaSpecifications.nombresApellidosContainsIgnoreCase(nombres, apellidos));
    }
    public List<Persona> buscarPorDni(String num_documento) {
        Specification<Persona> spec = PersonaSpecifications.num_documentoContainsIgnoreCase(num_documento);
        return repository.findAll(spec);
    }

    public List<Persona> obtenerPersonasActivas() {
        return repository.findByActivoTrue();
    }

}
