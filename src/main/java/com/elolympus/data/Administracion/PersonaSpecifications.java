package com.elolympus.data.Administracion;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PersonaSpecifications {
    public static Specification<Persona> nombresApellidosContainsIgnoreCase(String nombres, String apellidos) {
        return (root, query, criteriaBuilder) -> {
            Predicate nombresPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nombres")), "%" + nombres.toLowerCase() + "%");
            Predicate apellidosPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidos")), "%" + apellidos.toLowerCase() + "%");
            Predicate activoPredicate = criteriaBuilder.isTrue(root.get("activo"));
            return criteriaBuilder.and(nombresPredicate, apellidosPredicate, activoPredicate);
        };
    }
//    public static Specification<Persona> num_documentoContainsIgnoreCase(String num_documento) {
//        return (root, query, criteriaBuilder) -> {
//            if (num_documento == null || num_documento.isEmpty()) {
//                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Siempre verdadero, no filtra nada
//            }
//            return criteriaBuilder.like(root.get("num_documento"), "%" + num_documento + "%");
//        };
//    }
    public static Specification<Persona> num_documentoContainsIgnoreCase(String num_documento) {
        return (root, query, criteriaBuilder) -> {
            // Convertir el campo numérico DNI a texto
            Expression<String> dniAsString = criteriaBuilder.function("text", String.class, root.get("num_documento"));

            // Usar LIKE para realizar la búsqueda parcial
            return criteriaBuilder.like(dniAsString, "%" + num_documento + "%");
        };
    }

//    public static Specification<Persona> dniEquals(Integer num_documento) {
//        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("num_documento"), num_documento);
//    }


}
