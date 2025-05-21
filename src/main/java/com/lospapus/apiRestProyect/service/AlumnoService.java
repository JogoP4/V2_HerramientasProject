package com.lospapus.apiRestProyect.service;

import com.lospapus.apiRestProyect.model.Alumno;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class AlumnoService {
    Date fechaNacimiento = new Date();
    Date fechaRegistro = new Date();
    public List<Alumno> alumnoList = new ArrayList<>(
            List.of(
                    new Alumno(
                            1,
                            "Asdy",
                            "Valdivia",
                            fechaNacimiento,
                            "Av. Ignacio Merino",
                            "9494970174",
                            "asdy@hshd.com",
                            fechaRegistro,
                            true
                    )
            )
    );

    public List<Alumno> findAll() {
        return alumnoList;
    }

    public Alumno save(Alumno alumno) {
        alumnoList.add(alumno);
        return alumno;
    }

    public Optional<Alumno> getById(int id) {
        return alumnoList.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
    }

    public boolean deleteById(int id){
        return alumnoList.removeIf(x -> x.getId() == id);
    }

    public Optional<Alumno> editById(int id, Alumno alumnoModificar){
        return getById(id)
                .map(x -> {
                    x.setNombre(alumnoModificar.getNombre());
                    x.setApellido(alumnoModificar.getApellido());
                    x.setDireccion(alumnoModificar.getDireccion());
                    x.setTelefono(alumnoModificar.getTelefono());
                    x.setEmail(alumnoModificar.getEmail());
                    x.setFechaNacimiento(alumnoModificar.getFechaNacimiento());
                    x.setFechaRegistro(alumnoModificar.getFechaRegistro());
                    x.setActivo(alumnoModificar.isActivo());
                    return alumnoModificar;
                });
    }
}
