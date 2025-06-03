package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.infraestructure.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataInscripcionRepository extends JpaRepository<InscripcionesEntity, Integer> {
    List<InscripcionesEntity> findAll();

    Optional<InscripcionesEntity> findById(int id);

    @Query("SELECT n FROM InscripcionesEntity n WHERE n.cursoEntity.nombreAula = :nombreAula")
    List<NotaEntity> obtenerInscripcionesPorCurso(@Param("nombreAula") String nombreAula);

    @Query("SELECT n FROM InscripcionesEntity n WHERE n.alumno.dni = :nombreAula")
    List<AsignacionesEntity> obtenerInscripcionesPorAlumno(@Param("nombreAula") String dni);
}
