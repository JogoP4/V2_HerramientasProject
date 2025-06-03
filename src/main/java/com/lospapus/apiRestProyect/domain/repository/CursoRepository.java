package com.lospapus.apiRestProyect.domain.repository;

import com.lospapus.apiRestProyect.domain.model.Curso;

import java.util.List;


public interface CursoRepository {
    List<Curso> findAll();
    Curso save(Curso curso);
}
