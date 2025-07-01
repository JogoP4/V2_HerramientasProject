package com.lospapus;

import com.lospapus.apiRestProyect.application.Mapper.AplicacionMapper;
import com.lospapus.apiRestProyect.application.dto.ActualizarUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.CrearUsuarioRequestDTO;
import com.lospapus.apiRestProyect.application.dto.RolResponseDTO;
import com.lospapus.apiRestProyect.application.dto.UsuarioResponseDTO;
import com.lospapus.apiRestProyect.application.service.UsuarioService;
import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.domain.repository.RolRepository;
import com.lospapus.apiRestProyect.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UsuarioServiceTests {
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private RolRepository rolRepository;
    @Mock
    private AplicacionMapper mapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private AutoCloseable mocks;

    @BeforeEach
    void init() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("1) registrarUsuario → guarda y devuelve DTO cuando todo es válido")
    void registrarUsuario_ok() {

        CrearUsuarioRequestDTO req = givenCrearUsuario();
        Rol rol = new Rol(1, "ALUMNO");
        Usuario usuarioGuardado = givenUsuario(req, rol, 10);
        UsuarioResponseDTO dtoEsperado = new UsuarioResponseDTO(
                10, req.getName(), req.getDni(), req.getEmail(),
                req.getDireccion(), req.getTelefono(),
                req.getFechaNacimiento(), LocalDate.now(), new RolResponseDTO(1, "ALUMNO"), true);

        when(rolRepository.findByRol("ALUMNO")).thenReturn(Optional.of(rol));
        when(usuarioRepository.findByEmail(req.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(req.getPassword())).thenReturn("ENCODED");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);
        when(mapper.toUsuarioResponseDTO(usuarioGuardado)).thenReturn(dtoEsperado);


        UsuarioResponseDTO result = usuarioService.registrarUsuario(req);


        assertThat(result).usingRecursiveComparison().isEqualTo(dtoEsperado);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    @DisplayName("2) registrarUsuario → lanza excepción si el email ya existe")
    void registrarUsuario_emailDuplicado() {
        CrearUsuarioRequestDTO req = givenCrearUsuario();
        when(rolRepository.findByRol("ALUMNO")).thenReturn(Optional.of(new Rol(1, "ALUMNO")));
        when(usuarioRepository.findByEmail(req.getEmail())).thenReturn(Optional.of(new Usuario()));

        assertThatThrownBy(() -> usuarioService.registrarUsuario(req))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("ya está en uso");

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("3) actualizarUsuario → modifica campos y devuelve DTO")
    void actualizarUsuario_ok() {
        int id = 7;
        ActualizarUsuarioRequestDTO req = new ActualizarUsuarioRequestDTO();
        req.setName("Nuevo Nombre");
        req.setDireccion("Nueva Dir");
        req.setTelefono("987654321");
        req.setEmail("nuevo@email.com");

        Usuario existente = givenUsuarioExistente(id);
        Usuario actualizado = existente;
        actualizado.setName(req.getName());
        actualizado.setDireccion(req.getDireccion());
        actualizado.setTelefono(req.getTelefono());
        actualizado.setEmail(req.getEmail());

        UsuarioResponseDTO dtoEsperado = mock(UsuarioResponseDTO.class);

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(usuarioRepository.save(existente)).thenReturn(actualizado);
        when(mapper.toUsuarioResponseDTO(actualizado)).thenReturn(dtoEsperado);

        UsuarioResponseDTO result = usuarioService.actualizarUsuario(id, req);

        assertThat(result).isSameAs(dtoEsperado);
        verify(usuarioRepository).save(existente);
    }

    @Test
    @DisplayName("4) listarTodosUsuarios → devuelve lista mapeada")
    void listarTodosUsuarios() {
        Usuario u1 = new Usuario(); Usuario u2 = new Usuario();
        when(usuarioRepository.findAll()).thenReturn(List.of(u1, u2));
        when(mapper.toUsuarioResponseDTO(u1)).thenReturn(mock(UsuarioResponseDTO.class));
        when(mapper.toUsuarioResponseDTO(u2)).thenReturn(mock(UsuarioResponseDTO.class));

        List<UsuarioResponseDTO> lista = usuarioService.listarTodosUsuarios();

        assertThat(lista).hasSize(2);
        verify(mapper, times(2)).toUsuarioResponseDTO(any());
    }

    @Test
    @DisplayName("5) getEmailUsuario → devuelve email según ID")
    void getEmailUsuario() {
        int id = 5;
        Usuario u = new Usuario(); u.setEmail("prueba@mail.com");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(u));

        String email = usuarioService.obtenerEmailUsuario(id);

        assertThat(email).isEqualTo("prueba@mail.com");
    }

    // ---------- utilidades de prueba ----------
    private CrearUsuarioRequestDTO givenCrearUsuario() {
        CrearUsuarioRequestDTO r = new CrearUsuarioRequestDTO();
        r.setName("Juan");
        r.setDni("12345678");
        r.setDireccion("Dir 123");
        r.setTelefono("987654321");
        r.setEmail("juan@mail.com");
        r.setPassword("pass123");
        r.setNombreRol("ALUMNO");
        r.setFechaNacimiento(new Date());
        return r;
    }

    private Usuario givenUsuario(CrearUsuarioRequestDTO req, Rol rol, int id) {
        return new Usuario(
                id,
                req.getName(),
                req.getDni(),
                req.getDireccion(),
                req.getTelefono(),
                req.getEmail(),
                "ENCODED",
                req.getFechaNacimiento(),
                LocalDate.now(),
                true,
                rol
        );
    }

    private Usuario givenUsuarioExistente(int id) {
        Rol rol = new Rol(1, "ALUMNO");
        return new Usuario(
                id,"viejo", "87654321", "vieja dir",
                "123456789","viejo@mail.com",
                "pass", new Date(), LocalDate.now(), true, rol);
    }
}

//holi