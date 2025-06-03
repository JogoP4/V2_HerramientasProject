package com.lospapus.apiRestProyect.application.Mapper;

import com.lospapus.apiRestProyect.application.dto.*;
import com.lospapus.apiRestProyect.domain.model.Asignaciones;
import com.lospapus.apiRestProyect.domain.model.Nota;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignacionesEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.NotaEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.RolEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotaMapper {
    public static Nota toDomain(NotaEntity notaEntity) {
        Usuario usuarioDomain = UsuarioMapper.toDomain(notaEntity.getAlumno());
        Asignaciones asignacionesDomain = AsignacionesMapper.toDomain(notaEntity.getAsignacionesEntity());
        return new Nota(
                notaEntity.getId(),
                usuarioDomain,
                asignacionesDomain,
                notaEntity.getCalificacion(),
                notaEntity.getComentario(),
                notaEntity.getFechaRegistroNota()
        );
    }

    public static NotaEntity toEntity(Nota notaDomain) {
        NotaEntity entity = new NotaEntity();
        entity.setId(notaDomain.getId());
        entity.setCalificacion((double) notaDomain.getId());
        entity.setComentario(notaDomain.getComentario());
        entity.setFechaRegistroNota(notaDomain.getFechaRegistroNota());

        UsuarioEntity usuarioEntity = UsuarioMapper.toEntity(notaDomain.getAlumno());
        entity.setAlumno(usuarioEntity);

        AsignacionesEntity asignacionesEntity = AsignacionesMapper.toEntity(notaDomain.getAsignaciones());
        entity.setAsignacionesEntity(asignacionesEntity);

        return entity;
    }

    // Mapea de Modelo de Dominio a DTO de Respuesta
    public static NotaResponseDTO toNotaResponseDTO(Nota notaDomain) {
        if (notaDomain == null) {
            return null;
        }

        // Mapeamos el alumno (Modelo de Dominio) a su DTO
        UsuarioResponseDTO alumnoDto = UsuarioMapper.toUsuarioResponseDTO(notaDomain.getAlumno());

        // Mapeamos la asignación Profesor-Asignatura-Curso (Modelo de Dominio) a su DTO
        AsignacionResponseDTO pacDto = null;
        if (notaDomain.getAsignaciones() != null) {
            Asignaciones pacDomain = notaDomain.getAsignaciones();

            UsuarioResponseDTO profesorDto = UsuarioMapper.toUsuarioResponseDTO(pacDomain.getProfesor());
            AsignaturaResponseDTO asignaturaDto = AsignaturaMapper.toAsignaturaResponseDTO(pacDomain.getAsignatura());
            CursoResponseDTO cursoDto = CursoMapper.toCursoResponseDTO(pacDomain.getCurso());

            pacDto = new AsignacionResponseDTO(
                    pacDomain.getId(),
                    profesorDto,
                    asignaturaDto,
                    cursoDto
            );
        }

        return new NotaResponseDTO(
                notaDomain.getId(),
                alumnoDto,
                pacDto,
                notaDomain.getCalificacion(),
                notaDomain.getFechaRegistroNota()
        );
    }

    // Mapea de DTO de Solicitud (Registrar Nota) a Modelo de Dominio
    // Nota: Los objetos Usuario y ProfesorAsignaturaCurso NO vienen completos en el DTO,
    // solo sus IDs. El servicio de aplicación DEBE buscar estos modelos de dominio
    // completos y asignarlos antes de pasar la Nota a persistencia.
    public static Nota toDomain(RegistrarNotaRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        // Creamos una Nota con los IDs de las relaciones.
        // El servicio de aplicación buscará los objetos completos Usuario y ProfesorAsignaturaCurso
        // y los asignará a la Nota antes de guardarla.
        // La fecha de registro normalmente se genera en el servicio.
        return new Nota(
                null, // El ID se asignará después de persistir
                null, // El objeto Usuario se establecerá en el servicio
                null, // El objeto ProfesorAsignaturaCurso se establecerá en el servicio
                requestDTO.getCalificacion(),
                requestDTO.getComentario(),
                null// La fecha de registro se establecerá en el servicio
        );
    }

}
