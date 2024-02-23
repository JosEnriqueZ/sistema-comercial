package com.elolympus.data.Administracion;

import com.elolympus.data.AbstractEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "rol", schema = "administracion")
public class Rol extends AbstractEntity {
    //++++++++++++++++++++++++++++ICCA+++++++++++++++++++++++++++++
    @Column(name = "creado", nullable = false)
    private LocalDateTime creado;
    @Column(name = "creador", length = 200, nullable = false)
    private String creador;
    @Column(name = "activo", nullable = false)
    private Boolean activo;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Column(name = "area", length = 100, nullable = false)
    private String area;
    @Column(name = "cargo", length = 100, nullable = false)
    private String cargo;
    @Column(name = "descripcion", length = 250, nullable = false)
    private String descripcion;

    //CRUD
    @Column(name = "create", nullable = false)
    private Boolean create;
    @Column(name = "read", nullable = false)
    private Boolean read;
    @Column(name = "update", nullable = false)
    private Boolean update;
    @Column(name = "delete", nullable = false)
    private Boolean delete;

    public Rol() {
    }

    public Rol(String creador, String area, String cargo, String descripcion, Boolean create, Boolean read, Boolean update, Boolean delete) {
        this.creador = creador;
        this.area = area;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
    }

    @PrePersist
    public void prePersist() {
        this.creado = LocalDateTime.now();
        try {
            // this.creador = authenticatedUser.get().get().getUsername();
        }catch (Exception e){
            System.out.println("ERROR al obtner el usuario: " + e.toString());
        }
        this.activo = true;
    }

    @Override
    public String toString() {
        return "Rol{" +
                ", creado=" + creado +
                ", creador='" + creador + '\'' +
                ", activo=" + activo +
                ", area='" + area + '\'' +
                ", cargo='" + cargo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", create=" + create +
                ", read=" + read +
                ", update=" + update +
                ", delete=" + delete +
                '}';
    }
    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
