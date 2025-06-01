package com.lospapus.apiRestProyect.application.dto;

import lombok.Getter;

@Getter
public class RolResponseDTO {
    private int id;
    private String nombreRol;

    public RolResponseDTO(int id, String nombreRol) {
        this.id = id;
        this.nombreRol = nombreRol;
    }
}
