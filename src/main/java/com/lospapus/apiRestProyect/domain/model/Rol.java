package com.lospapus.apiRestProyect.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    private Integer id;
    private String nombreRol;


    public boolean esAdministrador() {
        return "ADMINISTRADOR".equalsIgnoreCase(this.nombreRol);
    }
    public boolean esProfesor() {
        return "PROFESOR".equalsIgnoreCase(this.nombreRol);
    }
    public boolean esAlumno() {
        return "ALUMNO".equalsIgnoreCase(this.nombreRol);
    }
}
