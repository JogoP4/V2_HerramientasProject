package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.domain.model.Asignaciones;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignacionesEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.CursoEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.NotaEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataNotaRepository extends JpaRepository<NotaEntity, Integer> {
    Optional<NotaEntity> findById(int id);

    List<NotaEntity> findAll();

    @Query("SELECT n FROM NotaEntity n WHERE n.asignacionesEntity.cursoEntity.nombreAula = :nombreAula")
    List<NotaEntity> obtenerNotasPorCurso(@Param("nombreAula") String nombreAula);

    @Query("SELECT n FROM NotaEntity n WHERE n.alumno.id = :alumnoId")
    List<NotaEntity> obtenerNotaPorAlumno(@Param("dniAlumno") int alumnoId);

    @Query("SELECT n FROM NotaEntity n WHERE n.asignacionesEntity.profesor.id = :profesorId")
    List<NotaEntity> obtenerNotasPorProfesor(@Param("profesorId") Integer profesorId);

}
