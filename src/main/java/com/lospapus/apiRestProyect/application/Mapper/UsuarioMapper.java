package com.lospapus.apiRestProyect.application.Mapper;

import com.lospapus.apiRestProyect.application.dto.ActualizarUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.CrearUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.RolResponseDTO;
import com.lospapus.apiRestProyect.application.dto.UsuarioResponseDTO;
import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.RolEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class UsuarioMapper {
    public static Usuario toDomain(UsuarioEntity usuarioEntity) {
        Rol rolDomain = RolMapper.toDomain(usuarioEntity.getRolEntity());

        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getName(),
                usuarioEntity.getDni(),
                usuarioEntity.getDireccion(),
                usuarioEntity.getTelefono(),
                usuarioEntity.getEmail(),
                usuarioEntity.getPassword(),
                usuarioEntity.getFechaNacimiento(),
                usuarioEntity.getFechaRegistro(),
                usuarioEntity.isActive(),
                rolDomain
        );
    }

    public static UsuarioEntity toEntity(Usuario usuarioDomain) {
        UsuarioEntity entity = new UsuarioEntity();
        if (usuarioDomain.getId() != null) {
            entity.setId(usuarioDomain.getId());
        }
        entity.setName(usuarioDomain.getName());
        entity.setDni(usuarioDomain.getDni());
        entity.setDireccion(usuarioDomain.getDireccion());
        entity.setTelefono(usuarioDomain.getTelefono());
        entity.setEmail(usuarioDomain.getEmail());
        entity.setPassword(usuarioDomain.getPassword());
        entity.setFechaNacimiento(usuarioDomain.getFechaNacimiento());
        entity.setFechaRegistro(LocalDate.from(LocalDateTime.now()));
        entity.setActive(usuarioDomain.isActive());

        RolEntity rolEntity = RolMapper.toEntity(usuarioDomain.getRol());
        entity.setRolEntity(rolEntity);
        // MUY IMPORTANTE: passwordHash
        // Si el usuarioDomain viene de un CreacionDTO, su passwordHash no estará aquí.
        // La Entidad JPA SI necesita passwordHash. Este campo se establecerá
        // DESPUÉS del mapeo, por el servicio de aplicación que hasheó la contraseña.
        // O si es una actualización, se DEBE cargar la entidad existente para mantener el hash.
        // Por eso, NO copiamos el passwordHash directamente desde el modelo de dominio aquí.
        // usuarioEntity.setPasswordHash(usuarioDomain.getPasswordHash()); // << NO HACER ESTO, a menos que el dominio realmente lo maneje

        return entity;
    }

    public static void updateEntityFromDomain(Usuario usuarioDomain, UsuarioEntity usuarioEntity) {
        usuarioEntity.setName(usuarioDomain.getName());
        usuarioEntity.setDireccion(usuarioDomain.getDireccion());
        usuarioEntity.setTelefono(usuarioDomain.getTelefono());
        usuarioEntity.setEmail(usuarioDomain.getEmail());
    }


    // Mapea de Modelo de Dominio a DTO de Respuesta
    public static UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuarioDomain) {
        if (usuarioDomain == null) {
            return null;
        }
        RolResponseDTO rolDto = RolMapper.toRolResponseDTO(usuarioDomain.getRol());

        return new UsuarioResponseDTO(
                usuarioDomain.getId(),
                usuarioDomain.getName(),
                usuarioDomain.getDni(),
                usuarioDomain.getEmail(),
                usuarioDomain.getDireccion(),
                usuarioDomain.getTelefono(),
                usuarioDomain.getFechaNacimiento(),
                usuarioDomain.getFechaRegistro(),
                rolDto,
                usuarioDomain.isActive()
        );
    }

    // Mapea de DTO de Solicitud (Creación) a Modelo de Dominio
    public static Usuario toDomain(CrearUsuarioRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        return new Usuario(
                requestDTO.getName(),
                requestDTO.getDni(),
                requestDTO.getDireccion(),
                requestDTO.getTelefono(),
                requestDTO.getEmail(),
                requestDTO.getPassword(),
                requestDTO.getFechaNacimiento(),
                LocalDate.now(),
                true
        );
    }

    // Mapea de DTO de Solicitud (Actualización) para actualizar un Modelo de Dominio existente
    public static void updateDomainFromDto(ActualizarUsuarioRequestDTO requestDTO, Usuario usuarioDomain) {
        if (requestDTO == null || usuarioDomain == null) {
            return;
        }
        usuarioDomain.setName(requestDTO.getName());
        usuarioDomain.setDireccion(requestDTO.getDireccion());
        usuarioDomain.setTelefono(requestDTO.getTelefono());
        usuarioDomain.setEmail(requestDTO.getEmail());
    }
}
