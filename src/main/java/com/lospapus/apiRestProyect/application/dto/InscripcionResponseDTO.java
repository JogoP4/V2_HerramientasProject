package com.lospapus.apiRestProyect.application.dto;

public class InscripcionResponseDTO {
    private int id;
    private UsuarioResponseDTO alumno;
    private CursoResponseDTO curso;

    public InscripcionResponseDTO(int id, UsuarioResponseDTO alumno, CursoResponseDTO curso) {
        this.id = id;
        this.alumno = alumno;
        this.curso = curso;
    }
}
