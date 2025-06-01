package com.lospapus.apiRestProyect.application.Mapper;

import com.lospapus.apiRestProyect.application.dto.*;
import com.lospapus.apiRestProyect.domain.model.Asignatura;
import com.lospapus.apiRestProyect.domain.model.Curso;
import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignaturaEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.RolEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;

public class AsignaturaMapper {
    public static Asignatura toDomain(AsignaturaEntity asignaturaEntity) {

        return new Asignatura(
                asignaturaEntity.getId(),
                asignaturaEntity.getNombreAsignatura()
        );
    }

    public static AsignaturaEntity toEntity(Asignatura asignaturaDomain) {

        AsignaturaEntity asignaturaEntity = new AsignaturaEntity();
        asignaturaEntity.setId(asignaturaDomain.getId());
        asignaturaEntity.setNombreAsignatura(asignaturaDomain.getNombreAsignatura());
        return asignaturaEntity;
    }

    // Mapea de Modelo de Dominio a DTO de Respuesta
    public static AsignaturaResponseDTO toAsignaturaResponseDTO(Asignatura asignaturaDomain) {
        return new AsignaturaResponseDTO(
                asignaturaDomain.getId(),
                asignaturaDomain.getNombreAsignatura()
        );
    }

    // Mapea de DTO de Solicitud (Creación) a Modelo de Dominio
    public static Asignatura toDomain(CrearAsignaturaRequestDTO requestDTO) {

        return new Asignatura(
                null,
                requestDTO.getNombreAsignatura()
        );
    }

    public static void updateEntityFromDomain(Asignatura asignaturaDomain, AsignaturaEntity asignaturaEntity) {
        asignaturaEntity.setNombreAsignatura(asignaturaDomain.getNombreAsignatura());
    }
}
