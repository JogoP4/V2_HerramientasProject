package com.lospapus.apiRestProyect.application.dto;

import com.lospapus.apiRestProyect.domain.model.Rol;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class UsuarioResponseDTO {
    private int id;
    private String name;
    private String dni;
    private String email;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;
    private LocalDate fechaRegistro;
    private RolResponseDTO rol;
    private boolean active;

    public UsuarioResponseDTO(int id, String name, String dni, String email, String direccion, String telefono, Date fechaNacimiento, LocalDate fechaRegistro, RolResponseDTO rol, boolean active) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
        this.active = active;
    }
}
