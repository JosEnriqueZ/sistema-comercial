package com.elolympus.data.Logistica;

import com.elolympus.data.AbstractEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdenCompraDet> detalles;

    //Constructor
    public OrdenCompra() {
    }

    //Constructor con parametros
    public OrdenCompra(Integer almacenEntrega, Integer numeroProveedor, Integer direccionProveedor, Date fecha, Date fechaEntrega, Integer formaPago, Integer moneda, Integer impuesto, BigDecimal total, String observaciones, BigDecimal totalCobrado, BigDecimal tipoCambio, Integer diasCredito, Integer sucursal, Boolean impuesto_incluido, String documento_pago) {
        this.almacenEntrega = almacenEntrega;
        this.numeroProveedor = numeroProveedor;
        this.direccionProveedor = direccionProveedor;
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.formaPago = formaPago;
        this.moneda = moneda;
        this.impuesto = impuesto;
        this.total = total;
        this.observaciones = observaciones;
        this.totalCobrado = totalCobrado;
        this.tipoCambio = tipoCambio;
        this.diasCredito = diasCredito;
        this.sucursal = sucursal;
        this.impuesto_incluido = impuesto_incluido;
        this.documento_pago = documento_pago;
    }

    // Getters and Setters
    public Integer getAlmacenEntrega() {
        return almacenEntrega;
    }

    public void setAlmacenEntrega(Integer almacenEntrega) {
        this.almacenEntrega = almacenEntrega;
    }

    public Integer getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(Integer numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public Integer getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(Integer direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(Integer formaPago) {
        this.formaPago = formaPago;
    }

    public Integer getMoneda() {
        return moneda;
    }

    public void setMoneda(Integer moneda) {
        this.moneda = moneda;
    }

    public Integer getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Integer impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getTotalCobrado() {
        return totalCobrado;
    }

    public void setTotalCobrado(BigDecimal totalCobrado) {
        this.totalCobrado = totalCobrado;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public Integer getDiasCredito() {
        return diasCredito;
    }

    public void setDiasCredito(Integer diasCredito) {
        this.diasCredito = diasCredito;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public Boolean getImpuesto_incluido() {
        return impuesto_incluido;
    }

    public void setImpuesto_incluido(Boolean impuesto_incluido) {
        this.impuesto_incluido = impuesto_incluido;
    }

    public String getDocumento_pago() {
        return documento_pago;
    }

    public void setDocumento_pago(String documento_pago) {
        this.documento_pago = documento_pago;
    }

    public List<OrdenCompraDet> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<OrdenCompraDet> detalles) {
        this.detalles = detalles;
    }

    //ToString
    @Override
    public String toString() {
        return "OrdenCompra{" +
                "almacenEntrega=" + almacenEntrega +
                ", numeroProveedor=" + numeroProveedor +
                ", direccionProveedor=" + direccionProveedor +
                ", fecha=" + fecha +
                ", fechaEntrega=" + fechaEntrega +
                ", formaPago=" + formaPago +
                ", moneda=" + moneda +
                ", impuesto=" + impuesto +
                ", total=" + total +
                ", observaciones='" + observaciones + '\'' +
                ", totalCobrado=" + totalCobrado +
                ", tipoCambio=" + tipoCambio +
                ", diasCredito=" + diasCredito +
                ", sucursal=" + sucursal +
                ", impuesto_incluido=" + impuesto_incluido +
                ", documento_pago='" + documento_pago + '\'' +
                ", detalles=" + detalles +
                '}';
    }
}