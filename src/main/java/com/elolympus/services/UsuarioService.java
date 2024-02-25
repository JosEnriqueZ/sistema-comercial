package com.elolympus.services;

import com.elolympus.data.Administracion.Usuario;
import com.elolympus.services.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public int count() {
        return (int) repository.count();
    }

    // Método para encontrar todos los usuarios
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    // Método para guardar o actualizar un usuario
    public Usuario update(Usuario usuario) {
        // Aquí puedes añadir lógica antes de guardar el usuario
        return repository.save(usuario);
    }
    public Usuario save(Usuario usuario) {
        // Aquí puedes añadir lógica antes de guardar el usuario
        return repository.save(usuario);
    }

    // Método para eliminar un usuario
    public void delete(Usuario usuario) {
        repository.delete(usuario);
    }

    // Método para encontrar un usuario por ID
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }
}
