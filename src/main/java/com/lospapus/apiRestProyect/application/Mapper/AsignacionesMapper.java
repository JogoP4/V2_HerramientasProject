package com.lospapus.apiRestProyect.application.Mapper;

import com.lospapus.apiRestProyect.domain.model.*;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AsignacionesMapper {
    public static Asignaciones toDomain(AsignacionesEntity asignacionesEntity) {
        Usuario usuarioDomain = UsuarioMapper.toDomain(asignacionesEntity.getProfesor());
        Asignatura asignaturaDomain = AsignaturaMapper.toDomain(asignacionesEntity.getAsignaturaEntity());
        Curso cursoDomain = CursoMapper.toDomain(asignacionesEntity.getCursoEntity());
        return new Asignaciones(
                asignacionesEntity.getId(),
                cursoDomain,
                asignaturaDomain,
                usuarioDomain
        );
    }

    public static AsignacionesEntity toEntity(Asignaciones asignacionesDomain) {
        AsignacionesEntity entity = new AsignacionesEntity();

        entity.setId(asignacionesDomain.getId());

        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(asignacionesDomain.getProfesor());
        entity.setProfesor(usuarioEntity);

        AsignaturaEntity asignaturaEntity = AsignaturaMapper.toEntity(asignacionesDomain.getAsignatura());
        entity.setAsignaturaEntity(asignaturaEntity);

        CursoEntity cursoEntity = CursoMapper.toEntity(asignacionesDomain.getCurso());
        entity.setCursoEntity(cursoEntity);
        return entity;
    }
}
