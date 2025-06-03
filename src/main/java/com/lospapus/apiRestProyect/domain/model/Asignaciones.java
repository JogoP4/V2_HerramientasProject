package com.lospapus.apiRestProyect.domain.model;

import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignaturaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Asignaciones {
    private Integer id;
    private Usuario profesor;
    private Asignatura asignatura;
    private Curso curso;

    public void asignarNuevoProfesor(Usuario nuevoProfesor){
        if(profesor == null || !profesor.esProfesor()){
            throw new IllegalArgumentException("El usuario debe ser un profesor para asignarlo a una materia");

        }
        this.profesor = nuevoProfesor;
    }

    public Asignaciones(int id, Curso curso, Asignatura asignatura, Usuario profesor) {
        if(profesor == null || !profesor.esProfesor()){
            throw new IllegalArgumentException("El usuario debe ser un profesor para asignarlo a una materia");

        }
        this.id = id;
        this.curso = curso;
        this.asignatura = asignatura;
        this.profesor = profesor;
    }

    public Asignaciones(Curso curso, Asignatura asignatura, Usuario profesor) {
        if(profesor == null || !profesor.esProfesor()){
            throw new IllegalArgumentException("El usuario debe ser un profesor para asignarlo a una materia");

        }
        this.curso = curso;
        this.asignatura = asignatura;
        this.profesor = profesor;
    }
}
