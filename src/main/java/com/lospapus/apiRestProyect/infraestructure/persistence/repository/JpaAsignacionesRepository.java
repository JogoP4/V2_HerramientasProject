package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.application.Mapper.*;
import com.lospapus.apiRestProyect.domain.model.Asignaciones;
import com.lospapus.apiRestProyect.domain.repository.AsignacionesRepository;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaAsignacionesRepository implements AsignacionesRepository {
    private final SpringDataAsignacionRepository asignacionRepository;
    private final SpringDataAsignaturaRepository asignaturaRepository;
    private final SpringDataCursoRepository cursoRepository;
    private final SpringDataUsuarioRepository usuarioRepository;

    public JpaAsignacionesRepository(SpringDataAsignacionRepository asignacionRepository,SpringDataAsignaturaRepository asignaturaRepository, SpringDataCursoRepository cursoRepository, SpringDataUsuarioRepository usuarioRepository) {
        this.asignacionRepository = asignacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Optional<Asignaciones> buscarPorId(int id) {
        return asignacionRepository.findById(id)
                .map(entity -> new Asignaciones(entity.getId(), CursoMapper.toDomain(entity.getCursoEntity()), AsignaturaMapper.toDomain(entity.getAsignaturaEntity()), UsuarioMapper.toDomain(entity.getProfesor())));
    }

    @Override
    public Asignaciones save(Asignaciones asignacionesDomain) {
        AsignacionesEntity asignacionesEntity;
        if (asignacionesDomain.getId() != null) {
            asignacionesEntity = asignacionRepository.findById(asignacionesDomain.getId())
                    .orElseThrow(() -> new RuntimeException("Asignacion no encontrada para actualizar: " + asignacionesDomain.getId()));
        } else {
            asignacionesEntity = new AsignacionesEntity();
        }


        UsuarioEntity profesorEntity = usuarioRepository.findById(asignacionesDomain.getProfesor().getId())
                .orElseThrow(() -> new RuntimeException("Entidad de Profesor no encontrada con ID: " + asignacionesDomain.getProfesor().getId()));
        asignacionesEntity.setProfesor(profesorEntity);

        CursoEntity cursoEntity = cursoRepository.findById(asignacionesDomain.getCurso().getId())
                .orElseThrow(() -> new RuntimeException("Entidad de Curso no encontrada con ID: " + asignacionesDomain.getCurso().getId()));
        asignacionesEntity.setCursoEntity(cursoEntity);

        AsignaturaEntity asignaturaEntity = asignaturaRepository.findById(asignacionesDomain.getAsignatura().getId())
                .orElseThrow(() -> new RuntimeException("Entidad de Asignatura no encontrada con ID: " + asignacionesDomain.getAsignatura().getId()));
        asignacionesEntity.setAsignaturaEntity(asignaturaEntity);

        AsignacionesEntity savedEntity = asignacionRepository.save(asignacionesEntity);

        return AsignacionesMapper.toDomain(savedEntity);
    }

    @Override
    public List<Asignaciones> mostrarAsignacionesPorProfesor(int profesorId) {
        return asignacionRepository.obtenerAsignacionesPorProfesor(profesorId).stream()
                .map(AsignacionesMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Asignaciones> mostrarAsignacionesPorCurso(String nombreCurso) {
        return asignacionRepository.obtenerAsignacionesPorCurso(nombreCurso).stream()
                .map(AsignacionesMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Asignaciones> mostrarAsignacionesPorAsignatura(String nombreAsignatura) {
        return asignacionRepository.obtenerAsignacionesPorAsignatura(nombreAsignatura).stream()
                .map(AsignacionesMapper::toDomain)
                .collect(Collectors.toList());
    }
}
