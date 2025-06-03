package com.lospapus.apiRestProyect.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumno", nullable = false)
    private UsuarioEntity alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor_asignatura_curso", nullable = false)
    private AsignacionesEntity asignacionesEntity;

    @Column(name = "calificacion", nullable = false)
    private Double calificacion;

    @Column(name = "comentario", nullable = true)
    private String comentario;

    @Column(name = "fecha_registro_nota", nullable = false)
    private LocalDate fechaRegistroNota;
}