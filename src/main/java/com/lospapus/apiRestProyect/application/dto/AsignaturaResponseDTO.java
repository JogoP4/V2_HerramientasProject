package com.lospapus.apiRestProyect.application.dto;

import lombok.Getter;

@Getter
public class AsignaturaResponseDTO {
    private int id;
    private String nombreAsignatura;

    public AsignaturaResponseDTO(int id, String nombreAsignatura) {
        this.id = id;
        this.nombreAsignatura = nombreAsignatura;
    }
}
