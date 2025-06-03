package com.lospapus.apiRestProyect.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarUsuarioRequestDTO {
    // ID no incluido aquí; se pasaría en el path de la URL (ej. PUT /usuarios/{id})

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La direccion puede estar vacío")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El telefono debe ser valido")
    private String telefono;

}
