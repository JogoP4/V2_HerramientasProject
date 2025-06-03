package com.lospapus.apiRestProyect.infraestructure.rest.controller.controller;

import com.lospapus.apiRestProyect.application.dto.AsignaturaResponseDTO;
import com.lospapus.apiRestProyect.application.dto.NotaResponseDTO;
import com.lospapus.apiRestProyect.application.service.AsignaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/asignatura")
public class AsignaturaController {
    private final AsignaturaService asignaturaService;

    public AsignaturaController(AsignaturaService asignaturaService){
        this.asignaturaService = asignaturaService;
    }

    @GetMapping
    public ResponseEntity<List<AsignaturaResponseDTO>> listarTodasAsignaturas(){
        List<AsignaturaResponseDTO> allAsignaturas = asignaturaService.listarTodasAsignaturas();
        return ResponseEntity.ok(allAsignaturas);
    }
}
