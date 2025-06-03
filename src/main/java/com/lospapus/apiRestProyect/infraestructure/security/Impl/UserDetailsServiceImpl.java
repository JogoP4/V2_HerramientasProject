package com.lospapus.apiRestProyect.infraestructure.security.Impl;

import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        //El debuggeador de pibes

        System.out.println(usuario);
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getPassword());
        System.out.println(usuario.getRol().getNombreRol());


        return new User(usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRol() != null ?
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombreRol())
                        ) :
                        Collections.emptyList());

    }
}

