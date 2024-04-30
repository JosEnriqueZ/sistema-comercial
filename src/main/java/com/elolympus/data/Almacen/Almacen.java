package com.elolympus.data.Almacen;

import com.elolympus.data.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "almacen", schema = "almacen")
public class Almacen extends AbstractEntity {

    @Column(name = "sucursal")
    private Integer sucursal;
    @Column(name = "codigo")
    private Integer Codigo;
    @Column(name = "descripcion")
    private String Descripcion;
}
