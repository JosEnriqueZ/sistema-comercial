package com.elolympus.services;

import com.elolympus.services.repository.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    private final RolRepository repository;
    public RolService(RolRepository repository) {
        this.repository=repository;
    }

    public int count() {
        return (int) repository.count();
    }



}
