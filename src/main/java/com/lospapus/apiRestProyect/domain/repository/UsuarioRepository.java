package com.lospapus.apiRestProyect.domain.repository;

import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.model.Usuario;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(int id);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
    List<Usuario> findByRol(Rol rol);
}
