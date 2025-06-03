package com.lospapus.apiRestProyect.service;

import com.lospapus.apiRestProyect.model.Alumno;
import com.lospapus.apiRestProyect.model.Curso;
import org.springframework.stereotype.Service;
import com.lospapus.apiRestProyect.model.Nota;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class NotaService {
    Alumno alumno = new Alumno(1, "Juan", "Zavala", new Date(), "Av. La planicie", "9211234381", "juan@mail.com", new Date(), true);
    Curso curso = new Curso(1, "Geografia", "2024", "5to A");

    // Crear fecha específica: 5 de abril de 2025 (solo fecha, sin hora)
    private Date crearFecha5Abril2025() {
        Calendar calendario = Calendar.getInstance();
        calendario.clear(); // Elimina todos los valores
        calendario.set(Calendar.YEAR, 2025);
        calendario.set(Calendar.MONTH, Calendar.APRIL);
        calendario.set(Calendar.DAY_OF_MONTH, 5);
        return calendario.getTime();
    }

    private final List<Nota> notas = new ArrayList<>(
            List.of(
                    new Nota(1, 8.5, crearFecha5Abril2025(), alumno, curso)
            )
    );

    public Nota save(Nota nota) {
        notas.add(nota);
        return nota;
    }

    public List<Nota> findAll() {
        return new ArrayList<>(notas);
    }

    public Nota findById(int id) {
        return notas.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }

    //Nuevo metodo para manejar la lógica de actualización S11
}