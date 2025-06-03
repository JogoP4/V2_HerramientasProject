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

    // Mapeo para Nota
    public NotaResponseDTO toNotaResponseDTO(Nota notaDomain) {
        return NotaMapper.toNotaResponseDTO(notaDomain);
    }
    public Nota toDomain(RegistrarNotaRequestDTO requestDTO) {
        return NotaMapper.toDomain(requestDTO);
    }

    //Mapeo para asignatura
    public AsignaturaResponseDTO toAsignaturaResponseDTO(Asignatura asignaturaDomain) {
        return AsignaturaMapper.toAsignaturaResponseDTO(asignaturaDomain);
    }
    public Asignatura toDomain(CrearAsignaturaRequestDTO requestDTO) {
        return AsignaturaMapper.toDomain(requestDTO);
    }

    //Mapeo para Curso
    public CursoResponseDTO toCursoResponseDTO(Curso cursoDomain){
        return CursoMapper.toCursoResponseDTO(cursoDomain);
    }
    public Curso toDomain(CrearCursoRequestDTO requestDTO){
        return CursoMapper.toDomain(requestDTO);
    }

}
