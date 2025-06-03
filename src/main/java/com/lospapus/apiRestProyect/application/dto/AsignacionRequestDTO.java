package com.lospapus.apiRestProyect.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignacionRequestDTO {
    @NotNull(message = "El ID del profesor no puede ser nulo")
    private int idProfesor;

    @NotNull(message = "El ID de la asignatura no puede ser nulo")
    private int idAsignatura;

    @NotNull(message = "El ID del curso no puede ser nulo")
    private int idCurso;
}
