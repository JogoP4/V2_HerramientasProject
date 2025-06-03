package com.lospapus.apiRestProyect.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearAsignaturaRequestDTO {
    @NotBlank(message = "EL nombre de la asignatura no puede estar vacío")
    private String nombreAsignatura;
}
