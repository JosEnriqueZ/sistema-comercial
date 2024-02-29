package com.elolympus.data.Logistica;

import com.elolympus.data.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "orden_compra_det",schema = "logistica")
public class OrdenCompraDet extends AbstractEntity {
    @Column(name = "orden_compra")
    private Integer ordenCompra;

    @Column(name = "producto")
    private Integer producto;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "descuento")
    private BigDecimal descuento;

    @Column(name = "almacen")
    private Integer almacen;

    @Column(name = "cantidad_tg")
    private BigDecimal cantidadTg;

    @Column(name = "lote")
    private String lote;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "cantidad_usada")
    private BigDecimal cantidadUsada;

    @Column(name = "cantidad_fraccion")
    private BigDecimal cantidadFraccion;
}
