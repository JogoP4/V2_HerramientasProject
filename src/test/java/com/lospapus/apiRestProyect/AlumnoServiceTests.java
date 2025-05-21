package com.lospapus.apiRestProyect;

import com.lospapus.apiRestProyect.model.Alumno;
import com.lospapus.apiRestProyect.service.AlumnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTests {
    @InjectMocks
    private AlumnoService alumnoService;

    @Test
    void testAddAlumno() {
        Date fechaNacimiento = new Date();
        Date fechaRegistro = new Date();

        Alumno alumno = new Alumno(
                2,
                "Juan",
                "Pérez",
                fechaNacimiento,
                "Calle Falsa 123",
                "123456789",
                "juan@example.com",
                fechaRegistro,
                true
        );
        alumnoService.save(alumno);

        List<Alumno> resultado = alumnoService.findAll();
        assertEquals(2, resultado.size());
        assertEquals("Juan", resultado.get(1).getNombre());
        assertEquals(alumno, resultado.get(1));

    }
}
