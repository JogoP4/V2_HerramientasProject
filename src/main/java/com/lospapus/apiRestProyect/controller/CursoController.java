package com.lospapus.apiRestProyect.controller;

import com.lospapus.apiRestProyect.model.Curso;
import com.lospapus.apiRestProyect.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> getAll(){
        return cursoService.findAll();
    }

    @PostMapping
    public Curso create(@RequestBody Curso curso){
        return  cursoService.save(curso);
    }

}
