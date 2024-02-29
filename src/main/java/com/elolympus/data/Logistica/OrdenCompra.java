package com.elolympus.data.Logistica;

import com.elolympus.data.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orden_compra", schema = "logistica")
public class OrdenCompra extends AbstractEntity {

    @Column(name = "almacen_entrega")
    private Integer almacenEntrega;

    @Column(name = "numero_proveedor")
    private Integer numeroProveedor;

    @Column(name = "direccion_proveedor")
    private Integer direccionProveedor;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "fecha_entrega")
    private Date fechaEntrega;

    @Column(name = "forma_pago")
    private Integer formaPago;

    @Column(name = "moneda")
    private Integer moneda;

    @Column(name = "impuesto")
    private Integer impuesto;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "total_cobrado")
    private BigDecimal totalCobrado;

    @Column(name = "tipo_cambio")
    private BigDecimal tipoCambio;

    @Column(name = "dias_credito")
    private Integer diasCredito;

    @Column(name = "sucursal")
    private Integer sucursal;

    @Column(name = "impuesto_incluido")
    private Boolean impuesto_incluido;
    @Column(name="documento_pago")
    private String documento_pago;

    public OrdenCompra() {
    }

}