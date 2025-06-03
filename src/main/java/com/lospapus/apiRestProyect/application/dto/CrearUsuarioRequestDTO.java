package com.lospapus.apiRestProyect.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CrearUsuarioRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El dni no puede estar vacío")
    @Size(min = 8, max = 8, message = "El dni debe ser valido")
    private String dni;

    @NotBlank(message = "La direccion puede estar vacío")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El telefono debe ser valido")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El rol no puede estar vacío")
    private String nombreRol;

    @NotBlank(message = "La fecha de nacimiento no puede estar vacío")
    private Date fechaNacimiento;

}
