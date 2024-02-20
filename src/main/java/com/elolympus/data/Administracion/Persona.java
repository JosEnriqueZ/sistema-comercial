package com.elolympus.data.Administracion;

import com.elolympus.data.AbstractEntity;
import com.elolympus.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import jakarta.persistence.*;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "persona", schema = "administracion")
public class Persona extends AbstractEntity {

    //public AuthenticatedUser authenticatedUser;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "creado", nullable = false)
    private LocalDateTime creado;
    @Column(name = "creador", length = 200, nullable = false)
    private String creador;
    @Column(name = "activo", nullable = false)
    private Boolean activo;
    @Column(name = "apellidos", length = 250, nullable = false)
    private String apellidos;
    @Column(name = "nombres", length = 250, nullable = false)
    private String nombres;
    @Column(name = "sexo", length = 1, nullable = false)
    private String sexo;

    //int 1-DNI 2-RUC 3-CARNET-EXTREANJERA
    @Column(name = "tipo_documento", length = 1, nullable = false)
    private int tipo_documento;
    @Column(name = "num_documento", length = 30, nullable = false)
    private int num_documento;
    @Column(name = "email", length = 250, nullable = false)
    private String email;
    @Column(name = "celular", length = 15, nullable = false)
    private int celular;

    public Persona() {
    }

    public Persona(String apellidos, String nombres, String sexo, Integer tipo_documento, Integer num_documento, String email, Integer celular) {
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.sexo = sexo;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.email = email;
        this.celular = celular;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(id, persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "apellidos='" + apellidos + '\'' +
                ", nombres='" + nombres + '\'' +
                ", tipo_documento=" + tipo_documento +
                ", num_documento=" + num_documento +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(Integer tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento.toString();
    }

    public void setNum_documento(String num_documento) {
        this.num_documento= Integer.parseInt(num_documento);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular.toString();
    }

    public void setCelular(String celular) {
        this.celular= Integer.parseInt(celular);
    }

}
