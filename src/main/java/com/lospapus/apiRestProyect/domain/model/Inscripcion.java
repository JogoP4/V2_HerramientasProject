package com.lospapus.apiRestProyect.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class Inscripcion {
    private int id;
    private Usuario alumno;
    private Curso curso;
    private LocalDate fechaInscripcion;

    public Inscripcion(Usuario alumno, Curso curso, LocalDate fechaInscripcion) {
        this.alumno = alumno;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
    }

}
