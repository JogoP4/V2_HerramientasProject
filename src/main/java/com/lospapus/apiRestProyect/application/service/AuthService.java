package com.lospapus.apiRestProyect.application.service;

import com.lospapus.apiRestProyect.application.dto.AuthRequestDTO;
import com.lospapus.apiRestProyect.application.dto.AuthResponseDTO;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.domain.repository.UsuarioRepository;
import com.lospapus.apiRestProyect.infraestructure.security.Impl.UserDetailsServiceImpl;
import com.lospapus.apiRestProyect.infraestructure.security.Jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository; // Para obtener detalles del usuario

    public AuthService(AuthenticationManager authenticationManager,
                       UserDetailsServiceImpl userDetailsService,
                       JwtUtil jwtUtil,
                       UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    public AuthResponseDTO authenticate(AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);

        Usuario usuario = usuarioRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado después de autenticación."));

        return new AuthResponseDTO(jwt, usuario.getEmail(), usuario.getRol().getNombreRol());
    }
}
