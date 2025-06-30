package com.lospapus.apiRestProyect.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Usuario {
    private Integer id;
    private String name;
    private String dni;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
    private Date fechaNacimiento;
    private LocalDate fechaRegistro;
    private boolean active;
    private Rol rol;

    public Usuario(Integer id, String name, String dni, String direccion, String telefono, String email, String password, Date fechaNacimiento, LocalDate fechaRegistro, boolean active, Rol rol) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.active = active;
        this.rol = rol;
    }

    public Usuario(String name, String dni, String direccion, String telefono, String email, Date fechaNacimiento, LocalDate fechaRegistro, boolean active, Rol rol) {
        this.name = name;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = null;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.active = active;
        this.rol = rol;
    }

    public Usuario(String name, String dni, String direccion, String telefono, String email, String password, Date fechaNacimiento, LocalDate fechaRegistro, boolean active, Rol rol) {
        this.name = name;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.active = active;
        this.rol = rol;
    }

    public Usuario(String name, String dni, String direccion, String telefono, String email, String password, Date fechaNacimiento, LocalDate fechaRegistro, boolean active) {
        this.name = name;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.active = active;

    }


    public void actualizarInformacionContacto(String direccion, String telefono, String email) {
        if (email != null && !email.isEmpty()) {
            validarEmail(email);
            this.email = email;
        }
        if (telefono != null && !telefono.isEmpty()) {
            validarTelefono(telefono);
            this.telefono = telefono;
        }
        if (direccion != null) {
            this.direccion = direccion;
        }
    }

    public void desactivar() {
        this.active = false;
    }

    public void reactivar() {
        this.active = true;
    }

    private void validarEmail(String email) {
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("El correo no tiene un formato válido");
        }
    }

    private void validarTelefono(String telefono) {
        if (telefono == null || !telefono.matches("^\\d{9}$")) {
            throw new IllegalArgumentException("El teléfono debe contener 9 digitos");
        }
    }

    public boolean esAdministrador() {
        return this.rol != null && this.rol.esAdministrador();
    }

    public boolean esProfesor() {
        return this.rol != null && this.rol.esProfesor();
    }

    public boolean esAlumno() {
        return this.rol != null && this.rol.esAlumno();
    }
}
