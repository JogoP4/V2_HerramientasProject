package com.lospapus.apiRestProyect.infraestructure.rest.controller.controller;

import com.lospapus.apiRestProyect.application.dto.CursoResponseDTO;
import com.lospapus.apiRestProyect.application.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v1/curso")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarTodosCursos(){
        List<CursoResponseDTO> allCursos = cursoService.listarTodosCursos();
        return ResponseEntity.ok(allCursos);
    }
}