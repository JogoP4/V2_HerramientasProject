package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.infraestructure.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataAsignacionRepository extends JpaRepository<AsignacionesEntity, Integer>{
    Optional<AsignacionesEntity> findById(int id);

    List<AsignacionesEntity> findAll();

    @Query("SELECT n FROM AsignacionesEntity n WHERE n.profesor.id = :profesorId")
    List<AsignacionesEntity> obtenerAsignacionesPorProfesor(@Param("profesorId") Integer profesorId);

    @Query("SELECT n FROM AsignacionesEntity n WHERE n.cursoEntity.nombreAula = :nombreAula")
    List<AsignacionesEntity> obtenerAsignacionesPorCurso(@Param("nombreAula") String nombreAula);

    @Query("SELECT n FROM AsignacionesEntity n WHERE n.asignaturaEntity.nombreAsignatura = :nombreAsignatura")
    List<AsignacionesEntity> obtenerAsignacionesPorAsignatura(@Param("nombreAsignatura") String nombreAsignatura);

}
