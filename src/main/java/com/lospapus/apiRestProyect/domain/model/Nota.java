package com.lospapus.apiRestProyect.domain.model;

import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignacionesEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class Nota {
    private Integer id;
    private Usuario alumno;
    private Asignaciones asignaciones;
    private Double calificacion;
    private String comentario;
    private LocalDate fechaRegistroNota;

    public Nota(Integer id, Usuario alumno, Asignaciones asignaciones, Double calificacion, String comentario, LocalDate fechaRegistroNota) {
        validarCalificacion(calificacion);
        if (alumno == null || !alumno.esAlumno()) {
            throw new IllegalArgumentException("La nota debe ser asignada a un alumno válido.");
        }
        if (asignaciones == null) {
            throw new IllegalArgumentException("La nota debe estar asociada a una asignación de enseñanza válida.");
        }
        this.fechaRegistroNota = fechaRegistroNota;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.asignaciones = asignaciones;
        this.alumno = alumno;
        this.id = id;
    }

    public Nota(Usuario alumno, Asignaciones asignaciones, Double calificacion, String comentario, LocalDate fechaRegistroNota) {
        validarCalificacion(calificacion);
        this.alumno = alumno;
        this.asignaciones = asignaciones;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaRegistroNota = fechaRegistroNota;
    }

    public void actualizarNota(double nuevaCalificacion, String nuevoComentario) {
        validarCalificacion(nuevaCalificacion);
        this.calificacion = nuevaCalificacion;
        this.comentario = nuevoComentario;
    }

    private void validarCalificacion(double calificacion) {
        if (calificacion < 0.0 || calificacion > 20.0) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 20.");
        }
    }
}
