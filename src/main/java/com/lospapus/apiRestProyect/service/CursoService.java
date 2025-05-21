package com.lospapus.apiRestProyect.service;

import com.lospapus.apiRestProyect.model.Curso;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CursoService {
    public List<Curso> cursoList = new ArrayList<>(
            List.of(
                    new Curso(1,"Aritmetica","2025", "5toC"),
                    new Curso(2, "Razonamiento Verbal", "2025", "4toB"),
                    new Curso(3, "Algebra", "2025", "4toC"),
                    new Curso(4, "Historia universal", "2025","5toB")
            )
    );

    public List<Curso> findAll() {
        return cursoList;
    }

    public Curso save(Curso curso) {
        cursoList.add(curso);
        return curso;
    }
}
