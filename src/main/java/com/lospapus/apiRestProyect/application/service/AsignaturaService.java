package com.lospapus.apiRestProyect.application.service;

import com.lospapus.apiRestProyect.application.Mapper.AplicacionMapper;
import com.lospapus.apiRestProyect.application.Mapper.AsignaturaMapper;
import com.lospapus.apiRestProyect.application.dto.AsignaturaResponseDTO;
import com.lospapus.apiRestProyect.domain.model.Asignatura;
import com.lospapus.apiRestProyect.domain.repository.AsignaturaRepository;
import com.lospapus.apiRestProyect.infraestructure.persistence.entity.AsignaturaEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaService {
    private final AsignaturaRepository asignaturaRepository;
    private final AplicacionMapper mapper;

    public AsignaturaService(AsignaturaRepository asignaturaRepository, AplicacionMapper mapper){
        this.asignaturaRepository = asignaturaRepository;
        this.mapper = mapper;
    }

    public List<AsignaturaResponseDTO> listarTodasAsignaturas(){
        List<Asignatura> asignaturasDomain = asignaturaRepository.findAll();
        return asignaturasDomain.stream()
                .map(mapper::toAsignaturaResponseDTO)
                .collect(Collectors.toList());
    }
}
