package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.application.Mapper.NotaMapper;
import com.lospapus.apiRestProyect.domain.model.Nota;
import com.lospapus.apiRestProyect.domain.repository.NotaRepository;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignacionesEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.NotaEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaNotaRepository implements NotaRepository {
    private final SpringDataNotaRepository notaRepository;
    private final SpringDataUsuarioRepository usuarioRepository;
    private final SpringDataAsignacionRepository asignacionRepository;

    public JpaNotaRepository(SpringDataNotaRepository notaRepository,
                              SpringDataUsuarioRepository usuarioRepository,
                              SpringDataAsignacionRepository asignacionRepository) {
        this.notaRepository = notaRepository;
        this.usuarioRepository = usuarioRepository;
        this.asignacionRepository = asignacionRepository;
    }

    @Override
    public Optional<Nota> buscarPorId(int id) {
        return notaRepository.findById(id)
                .map(NotaMapper::toDomain);
    }

    @Override
    public Nota guardar(Nota notaDomain) {
        NotaEntity notaEntity;
        if (notaDomain.getId() != null) {
            notaEntity = notaRepository.findById(notaDomain.getId())
                    .orElseThrow(() -> new RuntimeException("Nota no encontrada para actualizar: " + notaDomain.getId()));
        } else {
            notaEntity = new NotaEntity();
        }

        notaEntity.setCalificacion(notaDomain.getCalificacion());
        notaEntity.setFechaRegistroNota(notaDomain.getFechaRegistroNota());
        notaEntity.setComentario(notaDomain.getComentario());

        UsuarioEntity alumnoEntity = usuarioRepository.findById(notaDomain.getAlumno().getId())
                .orElseThrow(() -> new RuntimeException("Entidad de Alumno no encontrada con ID: " + notaDomain.getAlumno().getId()));
        notaEntity.setAlumno(alumnoEntity);

        AsignacionesEntity asignacionesEntity = asignacionRepository.findById(notaDomain.getAsignaciones().getId())
                .orElseThrow(() -> new RuntimeException("Entidad de Asignación Profesor-Asignatura-Curso no encontrada con ID: " + notaDomain.getAsignaciones().getId()));
        notaEntity.setAsignacionesEntity(asignacionesEntity);

        NotaEntity savedEntity = notaRepository.save(notaEntity);

        return NotaMapper.toDomain(savedEntity);
    }


    @Override
    public List<Nota> buscarTodos() {
        return notaRepository.findAll().stream()
                .map(NotaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Nota> buscarNotasPorCurso(String nombreCurso) {
        return notaRepository.obtenerNotasPorCurso(nombreCurso).stream()
                .map(NotaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Nota> buscarNotasPorAlumno(int profesorId) {
        return notaRepository.obtenerNotaPorAlumno(profesorId).stream()
                .map(NotaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Nota> buscarNotasPorProfesor(int profesorId) {
        return notaRepository.obtenerNotasPorProfesor(profesorId).stream()
                .map(NotaMapper::toDomain)
                .collect(Collectors.toList());
    }


}
