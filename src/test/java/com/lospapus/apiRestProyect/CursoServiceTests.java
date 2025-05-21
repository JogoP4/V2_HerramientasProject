package com.lospapus.apiRestProyect;

import com.lospapus.apiRestProyect.model.Curso;
import com.lospapus.apiRestProyect.service.CursoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTests {
    @InjectMocks
    private CursoService cursoService;

    @Test
    void testAddCurso() {

        Curso curso = new Curso(
                5,
                "Aritmetica",
                "2024",
                "5to C"
        );
        cursoService.save(curso);

        List<Curso> resultado = cursoService.findAll();
        assertEquals(5, resultado.size());
        assertEquals("Aritmetica", resultado.get(4).getNombre());
        assertEquals(curso, resultado.get(4));

    }
}
