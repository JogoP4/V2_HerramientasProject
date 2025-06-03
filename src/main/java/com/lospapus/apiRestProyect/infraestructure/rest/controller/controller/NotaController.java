package com.lospapus.apiRestProyect.infraestructure.rest.controller.controller;

import com.lospapus.apiRestProyect.application.dto.NotaResponseDTO;
import com.lospapus.apiRestProyect.application.dto.RegistrarNotaRequestDTO;
import com.lospapus.apiRestProyect.application.dto.UsuarioResponseDTO;
import com.lospapus.apiRestProyect.application.service.NotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/nota")
public class NotaController {
    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> registrarNota(@Valid @RequestBody RegistrarNotaRequestDTO requestDTO) {
        NotaResponseDTO nuevaNota = notaService.registrarNota(requestDTO);
        return new ResponseEntity<>(nuevaNota, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NotaResponseDTO>> obtenerTodasNotas(){
        List<NotaResponseDTO> allNotas = notaService.listarTodoasNotas();
        return ResponseEntity.ok(allNotas);
    }


    @GetMapping("/{idAlumno}")
    public ResponseEntity<List<NotaResponseDTO>> obtenerNotasPorAlumno(@PathVariable int idAlumno) {
        List<NotaResponseDTO> notas = notaService.obtenerNotasDeAlumno(idAlumno);
        return ResponseEntity.ok(notas);
    }
}
