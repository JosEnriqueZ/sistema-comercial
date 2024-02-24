package com.elolympus.services.repository;

import com.elolympus.data.Administracion.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolRepository extends
        JpaRepository<Rol, Long>,
        JpaSpecificationExecutor<Rol> {
}
