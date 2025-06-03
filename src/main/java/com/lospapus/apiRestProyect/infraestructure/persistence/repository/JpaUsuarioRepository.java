package com.lospapus.apiRestProyect.infraestructure.persistence.repository;

import com.lospapus.apiRestProyect.application.Mapper.UsuarioMapper;
import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.domain.repository.UsuarioRepository;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.RolEntity;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUsuarioRepository implements UsuarioRepository {
    private final SpringDataUsuarioRepository usuarioRepository;
    private final SpringDataRolRepository rolRepository;

    public JpaUsuarioRepository(SpringDataUsuarioRepository usuarioRepository, SpringDataRolRepository rolRepository){
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {

        RolEntity rolEntity = rolRepository.findByNombreRol(usuario.getRol().getNombreRol()).orElseThrow(() -> new RuntimeException("Rol no encontrado: " + usuario.getRol().getNombreRol()));

        UsuarioEntity usuarioEntity;
        if (usuario.getId() != null) {
            usuarioEntity = usuarioRepository.findById(usuario.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado para actualizar: " + usuario.getId()));
            UsuarioMapper.updateEntityFromDomain(usuario, usuarioEntity);

        } else {
            usuarioEntity = UsuarioMapper.toEntity(usuario);
        }

        usuarioEntity.setRolEntity(rolEntity);

        UsuarioEntity savedEntity = usuarioRepository.save(usuarioEntity);
        return UsuarioMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findById(int id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(UsuarioMapper::toDomain);
    }


    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> findByRol(Rol rol) {
        RolEntity rolEntity = rolRepository.findByNombreRol(rol.getNombreRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rol.getNombreRol()));
        return usuarioRepository.findByRolEntity(rolEntity).stream()
                .map(UsuarioMapper::toDomain)
                .collect(Collectors.toList());
    }



    public boolean existsByEmail(String email) {
        return usuarioRepository.findByEmail(email).isEmpty();
    }
}
