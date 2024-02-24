package com.elolympus.services;

import com.elolympus.services.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public int count() {
        return (int) repository.count();
    }
}
