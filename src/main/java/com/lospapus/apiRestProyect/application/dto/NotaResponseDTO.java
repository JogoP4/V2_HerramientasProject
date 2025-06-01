package com.lospapus.apiRestProyect.application.dto;

import java.time.LocalDate;

public class NotaResponseDTO {
    private int id;
    private UsuarioResponseDTO alumno; // DTO anidado
    private AsignacionResponseDTO asignacionResponseDTO; // DTO anidado
    private Double calificacion;
    private LocalDate fechaRegistro;

    public NotaResponseDTO(int id, UsuarioResponseDTO alumno, AsignacionResponseDTO asignacionResponseDTO, Double calificacion, LocalDate fechaRegistro) {
        this.id = id;
        this.alumno = alumno;
        this.asignacionResponseDTO = asignacionResponseDTO;
        this.calificacion = calificacion;
        this.fechaRegistro = fechaRegistro;
    }
}
