package com.elolympus.services.specifications;

import com.elolympus.data.Administracion.Usuario;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecifications {

    public static Specification<Usuario> conEstadoActivo(Boolean activo) {
        return (root, query, criteriaBuilder) -> {
            if (activo == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Siempre verdadero si activo es nulo.
            }
            return criteriaBuilder.equal(root.get("activo"), activo);
        };
    }
    public static Specification<Usuario> porUsuarioYActivo(String usuario) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicadoUsuario = criteriaBuilder.equal(root.get("usuario"), usuario);
            Predicate predicadoActivo = criteriaBuilder.isTrue(root.get("activo"));

            return criteriaBuilder.and(predicadoUsuario, predicadoActivo);
        };
    }
}