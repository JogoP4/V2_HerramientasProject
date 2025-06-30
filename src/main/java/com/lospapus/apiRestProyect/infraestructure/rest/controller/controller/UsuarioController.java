package com.lospapus.apiRestProyect.infraestructure.rest.controller.controller;

import com.lospapus.apiRestProyect.application.dto.ActualizarUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.CrearUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.UsuarioResponseDTO;
import com.lospapus.apiRestProyect.application.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    //Acceder a la informacion de todos los alumnos
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodosusuarios(){
        List<UsuarioResponseDTO> usuarios =  usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    //Para registrar un usuario en el sistema.
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody CrearUsuarioRequestDTO requestDTO) {
        UsuarioResponseDTO nuevoUsuario = usuarioService.registrarUsuario(requestDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    //Poder acceder a usuario por "id". Para alumno, solo puede acceder a su propia información registrada
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('PROFESOR') or " +
            "(hasRole('ALUMNO') and principal.username == @usuarioService.getEmailUsuario(#id))")
    public ResponseEntity<Optional<UsuarioResponseDTO>> obtenerUsuarioPorId(@PathVariable int id) {
        Optional<UsuarioResponseDTO> usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }

    //Poder actualizar alumno
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@PathVariable int id, @Valid @RequestBody ActualizarUsuarioRequestDTO requestDTO) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.actualizarUsuario(id, requestDTO);
        return ResponseEntity.ok(usuarioActualizado);
    }

    //Poder desahibilitar
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<UsuarioResponseDTO> deshabilitarUsuario(@PathVariable int id){
        UsuarioResponseDTO usuarioDeshabilitado = usuarioService.deshabilitarUsuario(id);
        return ResponseEntity.ok(usuarioDeshabilitado);
    }
}
