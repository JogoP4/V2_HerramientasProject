package com.lospapus.apiRestProyect.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearCursoRequestDTO {
    @NotBlank(message = "EL nombre del curso no puede estar vacío")
    private String nombreCurso;

    @NotBlank(message = "El año academico no puede estar nulo")
    @Min(value = 1980, message = "Ponte serio p compare")
    private int anioAcademico;
}
