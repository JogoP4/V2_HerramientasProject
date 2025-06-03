package com.lospapus.apiRestProyect.domain.repository;

import com.lospapus.apiRestProyect.domain.model.Asignaciones;

import java.util.List;
import java.util.Optional;


public interface AsignacionesRepository {
    Optional<Asignaciones> buscarPorId(int id);
    Asignaciones save(Asignaciones asignaciones);
    List<Asignaciones> mostrarAsignacionesPorProfesor(int profesorId);
    List<Asignaciones> mostrarAsignacionesPorCurso(String nombreCurso);
    List<Asignaciones> mostrarAsignacionesPorAsignatura(String nombreAsignatura);

}
