package com.lospapus.apiRestProyect.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivilegedAction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    private Integer id;
    private String nombreAula;
    private int anioEscolar;
}
