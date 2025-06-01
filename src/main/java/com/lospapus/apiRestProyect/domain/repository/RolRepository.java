package com.lospapus.apiRestProyect.domain.repository;

import com.lospapus.apiRestProyect.domain.model.Rol;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository {
    Optional<Rol> findByRol(String nombreRol);
    Rol save(Rol rol);
}
