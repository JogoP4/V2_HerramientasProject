package com.lospapus.apiRestProyect.application.service;

import com.lospapus.apiRestProyect.application.Mapper.AplicacionMapper;
import com.lospapus.apiRestProyect.application.dto.ActualizarUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.CrearUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.UsuarioResponseDTO;
import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.domain.repository.RolRepository;
import com.lospapus.apiRestProyect.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final AplicacionMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, AplicacionMapper mapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        List<Usuario> usuariosDomain = usuarioRepository.findAll();

        return usuariosDomain.stream()
                .map(mapper::toUsuarioResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> obtenerPorId(int id) {
        return usuarioRepository.findById(id)
                .map(mapper::toUsuarioResponseDTO);
    }

    public String obtenerEmailUsuario(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.get().getEmail();
    }

    public UsuarioResponseDTO deshabilitarUsuario(int id) {
        Usuario usuarioPorDeshabilitar = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Usuario no encontrado con ID: " + id)));

        usuarioPorDeshabilitar.setActive(false);

        Usuario usuarioDeshabilitadoDomain = usuarioRepository.save(usuarioPorDeshabilitar);

        return mapper.toUsuarioResponseDTO(usuarioDeshabilitadoDomain);
    }

    @Transactional
    public UsuarioResponseDTO actualizarUsuario(int id, ActualizarUsuarioRequestDTO requestDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuarioExistente.setName(requestDTO.getName());
        usuarioExistente.setDireccion(requestDTO.getDireccion());
        usuarioExistente.setTelefono(requestDTO.getTelefono());
        if (!requestDTO.getEmail().equals(usuarioExistente.getEmail())) {
            usuarioExistente.setEmail(requestDTO.getEmail());
        }


        Usuario usuarioActualizadoDomain = usuarioRepository.save(usuarioExistente);
        return mapper.toUsuarioResponseDTO(usuarioActualizadoDomain);
    }

    @Transactional
    public UsuarioResponseDTO registrarUsuario(CrearUsuarioRequestDTO requestDTO) {
        Rol rol = rolRepository.findByRol(requestDTO.getNombreRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + requestDTO.getNombreRol()));

        if (usuarioRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está en uso: " + requestDTO.getEmail());
        }

        Usuario usuarioDomain = new Usuario(
                requestDTO.getName(),
                requestDTO.getDni(),
                requestDTO.getDireccion(),
                requestDTO.getTelefono(),
                requestDTO.getEmail(),
                passwordEncoder.encode(requestDTO.getPassword()),
                requestDTO.getFechaNacimiento(),
                LocalDate.now(),
                true,
                rol
        );

        System.out.println(usuarioDomain);

        Usuario usuarioGuardado = usuarioRepository.save(usuarioDomain);

        return mapper.toUsuarioResponseDTO(usuarioGuardado);
    }
}


//Commit-S37-Implementar paginación o búsqueda por nombre

//Tú finge que aqui se resuelve el Commit-S38-Implementar paginación o búsqueda por nombre