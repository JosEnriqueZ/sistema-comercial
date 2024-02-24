package com.elolympus.services;

import com.elolympus.data.Administracion.Rol;
import com.elolympus.services.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository repository;
    public RolService(RolRepository repository) {
        this.repository=repository;
    }

    // Método para recuperar todos los roles
    public List<Rol> findAll() {
        return repository.findAll();
    }

    // Método para guardar o actualizar un rol
    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    // Método para eliminar un rol
    public void delete(Rol rol) {
        repository.delete(rol);
    }

    // Método para buscar un rol por su ID
    public Optional<Rol> findById(Long id) {
        return repository.findById(id);
    }
    public Rol update(Rol rol) {
        return repository.save(rol);
    }

}
