package com.lospapus.apiRestProyect.application.service;

import com.lospapus.apiRestProyect.application.Mapper.AplicacionMapper;
import com.lospapus.apiRestProyect.application.dto.CursoResponseDTO;
import com.lospapus.apiRestProyect.domain.model.Curso;
import com.lospapus.apiRestProyect.domain.repository.CursoRepository;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.CursoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    private final AplicacionMapper mapper;

     public CursoService(CursoRepository cursoRepository, AplicacionMapper mapper){
         this.cursoRepository = cursoRepository;
         this.mapper = mapper;
     }

     public List<CursoResponseDTO> listarTodasAsignaturas(){
         List<Curso> cursosDomain = cursoRepository.findAll();
         return cursosDomain.stream()
                 .map(mapper::toCursoResponseDTO)
                 .collect(Collectors.toList());
     }
}
