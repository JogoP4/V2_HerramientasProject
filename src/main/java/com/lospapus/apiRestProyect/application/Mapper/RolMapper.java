package com.lospapus.apiRestProyect.application.Mapper;

import com.lospapus.apiRestProyect.application.dto.RolResponseDTO;
import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.RolEntity;

public class RolMapper {
    public static Rol toDomain(RolEntity rolEntity) {

        return new Rol(
                rolEntity.getId(),
                rolEntity.getNombreRol()
        );
    }

    public static RolEntity toEntity(Rol rolDomain) {

        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(rolDomain.getId());

        rolEntity.setNombreRol(rolDomain.getNombreRol());
        return rolEntity;
    }

    public static RolResponseDTO toRolResponseDTO(Rol rolDomain) {
        if (rolDomain == null) {
            return null;
        }
        return new RolResponseDTO(rolDomain.getId(), rolDomain.getNombreRol());
    }
}
