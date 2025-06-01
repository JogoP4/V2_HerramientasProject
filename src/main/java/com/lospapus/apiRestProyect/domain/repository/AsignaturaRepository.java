package com.lospapus.apiRestProyect.domain.repository;

import com.lospapus.apiRestProyect.domain.model.Asignatura;

import java.util.List;

public interface AsignaturaRepository {
    List<Asignatura> findAll();
    Asignatura save(Asignatura asignatura);
}
