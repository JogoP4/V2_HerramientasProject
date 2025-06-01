package com.lospapus.apiRestProyect.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionRequestDTO {
    @NotNull(message = "El ID del alumno no puede ser nulo")
    private int idAlumno;

    @NotNull(message = "El ID del curso no puede ser nulo")
    private int idCurso;
}
