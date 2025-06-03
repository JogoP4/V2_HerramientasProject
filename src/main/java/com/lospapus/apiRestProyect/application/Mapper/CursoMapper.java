package com.lospapus.apiRestProyect.application.Mapper;

import com.lospapus.apiRestProyect.application.dto.*;
import com.lospapus.apiRestProyect.domain.model.Asignatura;
import com.lospapus.apiRestProyect.domain.model.Curso;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignaturaEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.CursoEntity;

public class CursoMapper {
    public static Curso toDomain(CursoEntity cursoEntity) {

        return new Curso(
                cursoEntity.getId(),
                cursoEntity.getNombreAula(),
                cursoEntity.getAnioEscolar()
        );
    }

    public static CursoEntity toEntity(Curso cursoDomain) {
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setId(cursoDomain.getId());
        cursoEntity.setNombreAula(cursoDomain.getNombreAula());
        cursoEntity.setAnioEscolar(cursoDomain.getAnioEscolar());
        return cursoEntity;
    }

    // Mapea de Modelo de Dominio a DTO de Respuesta
    public static CursoResponseDTO toCursoResponseDTO(Curso cursoDomain) {
        return new CursoResponseDTO(
                cursoDomain.getId(),
                cursoDomain.getNombreAula(),
                cursoDomain.getAnioEscolar()
        );
    }

    // Mapea de DTO de Solicitud (Creación) a Modelo de Dominio
    public static Curso toDomain(CrearCursoRequestDTO requestDTO) {

        return new Curso(
                null,
                requestDTO.getNombreCurso(),
                requestDTO.getAnioAcademico()
        );
    }

    public static void updateEntityFromDomain(Curso cursoDomain, CursoEntity cursoEntity) {
        cursoEntity.setNombreAula(cursoDomain.getNombreAula());
        cursoEntity.setAnioEscolar(cursoDomain.getAnioEscolar());
    }

}
