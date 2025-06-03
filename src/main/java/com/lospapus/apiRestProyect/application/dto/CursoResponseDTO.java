package com.lospapus.apiRestProyect.application.dto;

import lombok.Getter;

@Getter
public class CursoResponseDTO {
    private int id;
    private String nombreAula;
    private int anioAcademico;

    public CursoResponseDTO(int id, String nombreAula, int anioAcademico) {
        this.id = id;
        this.nombreAula = nombreAula;
        this.anioAcademico = anioAcademico;
    }
}
