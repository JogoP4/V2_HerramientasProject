package com.lospapus.apiRestProyect.domain.repository;

import com.lospapus.apiRestProyect.domain.model.Nota;

import java.util.List;
import java.util.Optional;

public interface NotaRepository {
    Optional<Nota> buscarPorId(int id);
    Nota guardar(Nota nota);
    List<Nota> buscarTodos();
    List<Nota> buscarNotasPorCurso(String nombreCurso);
    List<Nota> buscarNotasPorAlumno(int alumnoId);
    List<Nota> buscarNotasPorProfesor(int profesorId);

}
