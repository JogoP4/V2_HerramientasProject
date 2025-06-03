package com.lospapus.apiRestProyect.application.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrarNotaRequestDTO {
    @NotNull(message = "El ID del alumno no puede ser nulo")
    private int idAlumno;

    // Aquí se podría pasar el ID de la asignación de enseñanza si el profesor está viendo una lista de sus asignaciones.
    // O bien, se podría inferir la asignación a partir del ID del profesor logueado, ID de asignatura y ID de curso.
    // Para simplificar, asumimos que el profesor sabe a qué asignación se refiere.
    @NotNull(message = "El ID de la asignación de profesor/asignatura/curso no puede ser nulo")
    private int idAsignacion;

    @NotNull(message = "La calificación no puede ser nula")
    @DecimalMin(value = "0.0", message = "La calificación mínima es 0.0")
    @DecimalMax(value = "20.0", message = "La calificación máxima es 20.0")
    private Double calificacion;

    private String comentario;
    // La fecha de registro puede ser generada por el backend si no se requiere que el usuario la envíe
    // private LocalDate fechaRegistro;
}
