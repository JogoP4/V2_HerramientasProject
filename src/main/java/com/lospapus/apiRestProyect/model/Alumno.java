package com.lospapus.apiRestProyect.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

/**
 * Representa la entidad Alumno en la base de datos.
 * Utiliza anotaciones de JPA para el mapeo de la tabla 'alumnos'.
 *
 * Se incluyen datos básicos del alumno como nombre, apellido, fecha de nacimiento, contacto y estado de actividad.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "alumnos")
public class Alumno {

    /** Identificador único del alumno (clave primaria, autogenerada). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del alumno (no puede ser nulo). */
    @Column(nullable = false)
    private String nombre;

    /** Apellido del alumno (no puede ser nulo). */
    @Column(nullable = false)
    private String apellido;

    /** Fecha de nacimiento del alumno. */
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    /** Dirección de residencia del alumno. */
    private String direccion;

    /** Número de teléfono del alumno. */
    private String telefono;

    /** Correo electrónico del alumno. */
    private String email;

    /** Fecha en la que el alumno fue registrado en el sistema. */
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    /** Indica si el alumno está activo o no. */
    private boolean activo;

    /**
     * Constructor con todos los atributos.
     *
     * @param id Identificador del alumno.
     * @param nombre Nombre del alumno.
     * @param apellido Apellido del alumno.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param direccion Dirección de residencia.
     * @param telefono Número telefónico.
     * @param email Correo electrónico.
     * @param fechaRegistro Fecha de registro en el sistema.
     * @param activo Estado de actividad del alumno.
     */
    public Alumno(int id, String nombre, String apellido, Date fechaNacimiento, String direccion, String telefono, String email, Date fechaRegistro, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }
}
