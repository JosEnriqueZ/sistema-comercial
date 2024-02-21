package com.elolympus.data.Administracion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PersonaRepository  extends
        JpaRepository<Persona, Long>,
        JpaSpecificationExecutor<Persona> {


}

