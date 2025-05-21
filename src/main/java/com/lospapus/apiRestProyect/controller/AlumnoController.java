package com.lospapus.apiRestProyect.controller;

import com.lospapus.apiRestProyect.model.Alumno;
import com.lospapus.apiRestProyect.service.AlumnoService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> getAll() {
        return alumnoService.findAll();
    }

    @PostMapping
    public Alumno create(@RequestBody Alumno alumno) {
        return alumnoService.save(alumno);
    }

    @GetMapping("/{id}")
    public Optional<Alumno> getById(@PathVariable int id) {
        return alumnoService.getById(id);
    }

    @PutMapping("/{id}")
    public Optional<Alumno> editById(@PathVariable int id, @RequestBody Alumno alumno) {
        return alumnoService.editById(id, alumno);
    }

    @DeleteMapping("/{id}")
    public boolean deleteByID(@PathVariable int id) {
        return alumnoService.deleteById(id);
    }
}
