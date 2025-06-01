package com.lospapus.apiRestProyect.infraestructure.config;

import com.lospapus.apiRestProyect.domain.model.Rol;
import com.lospapus.apiRestProyect.domain.model.Usuario;
import com.lospapus.apiRestProyect.domain.repository.RolRepository;
import com.lospapus.apiRestProyect.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class UserInitializer implements CommandLineRunner {
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInitializer(RolRepository rolRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initializeRoles();
        initializeAdminUser();
    }

    private void initializeRoles() {
        String[] roleNames = {"ADMINISTRADOR", "PROFESOR", "ALUMNO"};

        for (String roleName : roleNames) {
            Optional<Rol> existingRol = rolRepository.findByRol(roleName);
            if (existingRol.isEmpty()) {
                Rol newRol = new Rol(null, roleName);
                rolRepository.save(newRol);
                System.out.println("Rol '" + roleName + "' creado.");
            } else {
                System.out.println("Rol '" + roleName + "' ya existe.");
            }
        }
    }

    private void initializeAdminUser() {
        LocalDate fechaNacimientoLocal = LocalDate.of(2005, 8, 18);
        Date fechaNacimiento = Date.from(fechaNacimientoLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String adminEmail = "admingosu@gmail.com";
        String adminPassword = "jotarooo";

        Optional<Usuario> existingAdmin = usuarioRepository.findByEmail(adminEmail);

        if (existingAdmin.isEmpty()) {
            Optional<Rol> adminRolOptional = rolRepository.findByRol("ADMINISTRADOR");
            if (adminRolOptional.isEmpty()) {
                throw new IllegalStateException("El rol ADMINISTRADOR no existe. Asegúrate de que los roles se inicialicen primero.");
            }
            Rol adminRol = adminRolOptional.get();

            String encodedPassword = passwordEncoder.encode(adminPassword);

            // Crea una instancia de Usuario (Modelo de Dominio)
            Usuario adminUser = new Usuario(
                    "Administrador",
                    "72847636",
                    "Av. Ignacio Merino",
                    "920128840",
                    adminEmail,
                    encodedPassword,
                    fechaNacimiento,
                    LocalDate.now(),
                    true,
                    adminRol
            );

            usuarioRepository.save(adminUser);
            System.out.println("Usuario administrador '" + adminEmail + "' creado con contraseña por defecto.");
        } else {
            System.out.println("Usuario administrador '" + adminEmail + "' ya existe.");
        }
    }
}
