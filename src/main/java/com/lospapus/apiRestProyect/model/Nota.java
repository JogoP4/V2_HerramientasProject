
package com.lospapus.apiRestProyect.model;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Representa la entidad Nota en la base de datos.
 *
 * Cada instancia de Nota está asociada a un alumno y un curso específicos,
 * y almacena el valor de la nota obtenida.
 * Utiliza anotaciones de JPA para el mapeo de la tabla 'notas'.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "notas")
public class Nota {

    /** Identificador único de la nota (clave primaria, autogenerada). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Valor numérico de la nota (no puede ser nulo). */
    @Column(nullable = false)
    private double valor;

    /** Alumno al que pertenece esta nota. Es una relación muchos-a-uno. */
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    //Gaaaaa
    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;

    /** Curso al que pertenece esta nota. Es una relación muchos-a-uno. */
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    /**
     * Constructor con todos los atributos.
     *
     * @param id Identificador de la nota.
     * @param valor Valor de la nota.
     * @param alumno Alumno relacionado con la nota.
     * @param curso Curso relacionado con la nota.
     */
    public Nota(int id, double valor, Date fecha, Alumno alumno, Curso curso) {
        this.id = id;
        this.valor = valor;
        this.fecha = fecha;
        this.alumno = alumno;
        this.curso = curso;
    }
}
