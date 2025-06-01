package com.lospapus.apiRestProyect.application.service;

import com.lospapus.apiRestProyect.application.Mapper.AplicacionMapper;
import com.lospapus.apiRestProyect.application.dto.NotaResponseDTO;
import com.lospapus.apiRestProyect.application.dto.RegistrarNotaRequestDTO;
import com.lospapus.apiRestProyect.application.dto.UsuarioResponseDTO;
import com.lospapus.apiRestProyect.domain.model.Asignaciones;
import com.lospapus.apiRestProyect.domain.model.Nota;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.domain.repository.AsignacionesRepository;
import com.lospapus.apiRestProyect.domain.repository.NotaRepository;
import com.lospapus.apiRestProyect.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService {
    private final NotaRepository notaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AsignacionesRepository asignacionesRepository;
    private final AplicacionMapper aplicacionMapper;

    public NotaService(NotaRepository notaRepository,
                       UsuarioRepository usuarioRepository,
                       AsignacionesRepository asignacionesRepository,
                       AplicacionMapper aplicacionMapper) {
        this.notaRepository = notaRepository;
        this.usuarioRepository = usuarioRepository;
        this.asignacionesRepository = asignacionesRepository;
        this.aplicacionMapper = aplicacionMapper;
    }

    @Transactional
    public NotaResponseDTO registrarNota(RegistrarNotaRequestDTO requestDTO) {
        Usuario alumno = usuarioRepository.findById(requestDTO.getIdAlumno())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + requestDTO.getIdAlumno()));
        if (!alumno.esAlumno()) {
            throw new IllegalArgumentException("El usuario con ID " + requestDTO.getIdAlumno() + " no es un alumno.");
        }

        Asignaciones asignaciones = asignacionesRepository
                .buscarPorId(requestDTO.getIdAsignacion())
                .orElseThrow(() -> new RuntimeException("Asignación Profesor-Asignatura-Curso no encontrada con ID: " + requestDTO.getIdAsignacion()));

        Nota nuevaNotaDomain = new Nota(
                alumno,
                asignaciones,
                requestDTO.getCalificacion(),
                requestDTO.getComentario(),
                LocalDate.now()
        );

        Nota notaPersistidaDomain = notaRepository.guardar(nuevaNotaDomain);

        return aplicacionMapper.toNotaResponseDTO(notaPersistidaDomain);
    }

    public List<NotaResponseDTO> obtenerNotasDeAlumno(int idAlumno) {
        List<Nota> notasDomain = notaRepository.buscarNotasPorAlumno(idAlumno);

        return notasDomain.stream()
                .map(aplicacionMapper::toNotaResponseDTO)
                .collect(Collectors.toList());
    }

    public List<NotaResponseDTO> listarTodoasNotas(){
        List<Nota> notaDomain = notaRepository.buscarTodos();

        return notaDomain.stream()
                .map(aplicacionMapper::toNotaResponseDTO)
                .collect(Collectors.toList());
    }
}
