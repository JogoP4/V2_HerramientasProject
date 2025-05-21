package com.lospapus.apiRestProyect.model;

import lombok.*;
import jakarta.persistence.*;

/**
 * Representa la entidad Curso en la base de datos.
 *
 * Contiene información básica de un curso como el nombre, el año escolar y el aula correspondiente.
 * Utiliza anotaciones de JPA para el mapeo de la tabla 'cursos'.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cursos")
public class Curso {

    /** Identificador único del curso (clave primaria, autogenerada). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del curso (no puede ser nulo). */
    @Column(nullable = false)
    private String nombre;

    /** Año escolar asociado al curso (no puede ser nulo). */
    @Column(name = "ano_escolar", nullable = false)
    private String anoEscolar;

    /** Aula donde se dicta el curso (no puede ser nulo). */
    @Column(nullable = false)
    private String aula;

    /**
     * Constructor con todos los atributos.
     *
     * @param id Identificador del curso.
     * @param nombre Nombre del curso.
     * @param anoEscolar Año escolar al que pertenece el curso.
     * @param aula Aula asignada al curso.
     */
    public Curso(int id, String nombre, String anoEscolar, String aula) {
        this.id = id;
        this.nombre = nombre;
        this.anoEscolar = anoEscolar;
        this.aula = aula;
    }
}
