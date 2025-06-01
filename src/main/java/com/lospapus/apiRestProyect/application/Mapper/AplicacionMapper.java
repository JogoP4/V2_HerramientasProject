package com.lospapus.apiRestProyect.application.Mapper;

import com.lospapus.apiRestProyect.application.dto.*;
import com.lospapus.apiRestProyect.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class AplicacionMapper {
    // Mapeo para Usuario
    public UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuarioDomain) {
        return UsuarioMapper.toUsuarioResponseDTO(usuarioDomain);
    }
    public Usuario toDomain(CrearUsuarioRequestDTO requestDTO) {
        return UsuarioMapper.toDomain(requestDTO);
    }
    public void updateDomainFromDto(ActualizarUsuarioRequestDTO requestDTO, Usuario usuarioDomain) {
        UsuarioMapper.updateDomainFromDto(requestDTO, usuarioDomain);
    }


}
