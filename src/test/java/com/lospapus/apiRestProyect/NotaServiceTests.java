package com.lospapus.apiRestProyect;

import com.lospapus.apiRestProyect.model.Alumno;
import com.lospapus.apiRestProyect.model.Curso;
import com.lospapus.apiRestProyect.model.Nota;
import com.lospapus.apiRestProyect.service.CursoService;
import com.lospapus.apiRestProyect.service.NotaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class NotaServiceTests {
    @InjectMocks
    private NotaService notaService;

    @Test
    void testAddNota() {
        Alumno alumno = new Alumno(2, "Juan", "Zavala", new Date(), "Av. La planicie", "9211234381", "juan@mail.com",new Date(), true);
        Curso curso = new Curso(1, "Geografia", "2024", "5to A");

        Nota nota = new Nota(
                1, 8.5, alumno, curso
        );

        notaService.save(nota);

        List<Nota> notas = notaService.findAll();
        assertEquals(2, notas.size());
        assertEquals(8.5, notas.get(1).getValor());
    }
}
