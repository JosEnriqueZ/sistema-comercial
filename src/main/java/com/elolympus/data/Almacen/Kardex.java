package com.elolympus.data.Almacen;

import com.elolympus.data.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "kardex")
public class Kardex extends AbstractEntity {
    @Column(name = "orden_id")
    private Integer ordenId;

    @Column(name = "fecha")
    private Timestamp fecha;

    @Column(name = "fecha_orden")
    private Date fechaOrden;

    @Column(name = "movimiento")
    private String movimiento;

    @Column(name = "almacen")
    private Integer almacen;

    @Column(name = "origen")
    private String origen;

    @Column(name = "destino")
    private String destino;

    @Column(name = "precio_costo")
    private BigDecimal precioCosto;

    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @Column(name = "stock_anterior")
    private BigDecimal stockAnterior;

    @Column(name = "ingreso")
    private BigDecimal ingreso;

    @Column(name = "salida")
    private BigDecimal salida;

    @Column(name = "stock")
    private BigDecimal stock;

    @Column(name = "producto")
    private Integer producto;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    //Constructor
    public Kardex() {
    }

    //Constructor con parametros
    public Kardex(Integer ordenId, Timestamp fecha, Date fechaOrden, String movimiento, Integer almacen, String origen, String destino, BigDecimal precioCosto, BigDecimal precioVenta, BigDecimal stockAnterior, BigDecimal ingreso, BigDecimal salida, BigDecimal stock, Integer producto, Date fechaVencimiento) {
        this.ordenId = ordenId;
        this.fecha = fecha;
        this.fechaOrden = fechaOrden;
        this.movimiento = movimiento;
        this.almacen = almacen;
        this.origen = origen;
        this.destino = destino;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.stockAnterior = stockAnterior;
        this.ingreso = ingreso;
        this.salida = salida;
        this.stock = stock;
        this.producto = producto;
        this.fechaVencimiento = fechaVencimiento;
    }

    //Getters y Setters

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(BigDecimal precioCosto) {
        this.precioCosto = precioCosto;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getStockAnterior() {
        return stockAnterior;
    }

    public void setStockAnterior(BigDecimal stockAnterior) {
        this.stockAnterior = stockAnterior;
    }

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public BigDecimal getSalida() {
        return salida;
    }

    public void setSalida(BigDecimal salida) {
        this.salida = salida;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
