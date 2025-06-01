package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.application.Mapper.RolMapper;
import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.repository.RolRepository;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.RolEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRolRepository implements RolRepository {
    private final SpringDataRolRepository rolRepository;

    public JpaRolRepository(SpringDataRolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    @Override
    public Optional<Rol> findByRol(String nombreRol) {
        return rolRepository.findByNombreRol(nombreRol)
                .map(RolMapper::toDomain);
    }

    @Override
    public Rol save(Rol rol) {
        RolEntity rolEntity = RolMapper.toEntity(rol);
        RolEntity savedEntity = rolRepository.save(rolEntity);
        return RolMapper.toDomain(savedEntity);
    }
}
