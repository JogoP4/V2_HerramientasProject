package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.application.Mapper.AsignaturaMapper;
import com.lospapus.apiRestProyect.application.Mapper.UsuarioMapper;
import com.lospapus.apiRestProyect.domain.model.Asignatura;
import com.lospapus.apiRestProyect.domain.repository.AsignaturaRepository;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignaturaEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.RolEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaAsignaturaRepository implements AsignaturaRepository {
    private final SpringDataAsignaturaRepository asignaturaRepository;

    public JpaAsignaturaRepository(SpringDataAsignaturaRepository asignaturaRepository){
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public List<Asignatura> findAll() {
        return asignaturaRepository.findAll().stream()
                .map(AsignaturaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        AsignaturaEntity asignaturaEntity;
        if (asignatura.getId() != null) {
            asignaturaEntity = asignaturaRepository.findById(asignatura.getId())
                    .orElseThrow(() -> new RuntimeException("Asignatura no encontrado para actualizar: " + asignatura.getId()));
            AsignaturaMapper.updateEntityFromDomain(asignatura, asignaturaEntity);

        } else {
            asignaturaEntity = AsignaturaMapper.toEntity(asignatura);
        }

        AsignaturaEntity savedEntity = asignaturaRepository.save(asignaturaEntity);
        return AsignaturaMapper.toDomain(savedEntity);
    }
}
