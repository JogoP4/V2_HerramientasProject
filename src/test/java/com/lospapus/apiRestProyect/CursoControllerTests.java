package com.lospapus.apiRestProyect;

import com.lospapus.apiRestProyect.controller.CursoController;
import com.lospapus.apiRestProyect.model.Curso;
import com.lospapus.apiRestProyect.service.CursoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


@WebMvcTest(CursoController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class CursoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Test
    void TestGetCursos() throws Exception {
        List<Curso> cursos = List.of(new Curso(
                1,
                "Razonamiento Logico",
                "2025",
                "5toB"));
        when(cursoService.findAll()).thenReturn(cursos);

        mockMvc.perform(get("/api/cursos"))
                .andExpect(status().isOk())
                .andDo(document("get-cursos"));
    }

    @Test
    void TestPostCursos() throws Exception{
        Curso curso = new Curso(
                2,
                "Biología",
                "2025",
                "5to B"
        );
        when(cursoService.save(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(post("/api/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                              {
                              "id": 2,
                              "nombre": "Geografía",
                              "anoEscolar": "2023",
                               "aula": "5to A"
                                }
                            """))
                .andExpect(status().isOk())
                .andDo(document("post-curso"));
    }
}
