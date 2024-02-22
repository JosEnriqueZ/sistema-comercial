package com.elolympus.data.Administracion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository  extends
        JpaRepository<Persona, Long>,
        JpaSpecificationExecutor<Persona> {
    List<Persona> findByNombresContainingAndApellidosContaining(String nombres, String apellidos);
    //List<Persona> findByNombresContainingAndApellidosContainingAndActivoTrue(String nombres, String apellidos);

//    @Query("SELECT p FROM Persona p WHERE LOWER(p.nombres) LIKE LOWER(:nombres) AND LOWER(p.apellidos) LIKE LOWER(:apellidos) AND p.activo = true")
//    List<Persona> findByNombresAndApellidosIgnoreCaseAndActivoTrue(@Param("nombres") String nombres, @Param("apellidos") String apellidos);
    List<Persona> findByActivoTrue();
}