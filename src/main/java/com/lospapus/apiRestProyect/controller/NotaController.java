package com.lospapus.apiRestProyect.controller;

import com.lospapus.apiRestProyect.model.Nota;
import com.lospapus.apiRestProyect.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @PostMapping
    public Nota crearNota(@RequestBody Nota nota) {
        return notaService.save(nota);
    }

    @GetMapping
    public List<Nota> obtenerNotas() {
        return notaService.findAll();
    }

}
