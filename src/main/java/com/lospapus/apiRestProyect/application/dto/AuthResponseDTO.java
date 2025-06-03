package com.lospapus.apiRestProyect.application.dto;

import lombok.Getter;

@Getter
public class AuthResponseDTO {

    private String jwt;
    private String username;
    private String rol;

    public AuthResponseDTO(String jwt, String username, String rol) {
        this.jwt = jwt;
        this.username = username;
        this.rol = rol;
    }
}
