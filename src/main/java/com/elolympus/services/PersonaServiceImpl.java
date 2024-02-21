package com.elolympus.services;

import com.elolympus.data.Administracion.PersonaRepository;

public class PersonaServiceImpl extends PersonaService{

    public PersonaServiceImpl(PersonaRepository repository) {
        super(repository);
    }
}
