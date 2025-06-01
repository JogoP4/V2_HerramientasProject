package com.lospapus.apiRestProyect.application.dto;

import lombok.Getter;

@Getter
public class AsignacionResponseDTO {
    private int id;
    private UsuarioResponseDTO profesor;
    private AsignaturaResponseDTO asignatura;
    private CursoResponseDTO curso;

    public AsignacionResponseDTO(int id, UsuarioResponseDTO profesor, AsignaturaResponseDTO asignatura, CursoResponseDTO curso) {
        this.id = id;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.curso = curso;
    }
}
