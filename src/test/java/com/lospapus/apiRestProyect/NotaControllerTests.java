package com.lospapus.apiRestProyect;

import com.lospapus.apiRestProyect.controller.NotaController;
import com.lospapus.apiRestProyect.model.Alumno;
import com.lospapus.apiRestProyect.model.Curso;
import com.lospapus.apiRestProyect.model.Nota;
import com.lospapus.apiRestProyect.service.NotaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotaController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class NotaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaService notaService;

    @Test
    void testGetNotas() throws Exception {
        Alumno alumno = new Alumno(1, "Pepe", "Zapata", new Date(), "Av. La planicie", "9214234381", "pepe@mail.com", new Date(), true);
        Curso curso = new Curso(4, "Hisotria universal", "2025", "5to A");

        List<Nota> notas = List.of(new Nota(
                2,
                8.5,
                alumno,
                curso
        ));
        when(notaService.findAll()).thenReturn(notas);

        mockMvc.perform(get("/api/notas"))
                .andExpect(status().isOk())
                .andDo(document("get-notas"));
    }

    @Test
    void testPostNotas() throws Exception {
        Alumno alumno = new Alumno(1, "Pepe", "Zapata", new Date(), "Av. La planicie", "9214234381", "pepe@mail.com", new Date(), true);
        Curso curso = new Curso(4, "Hisotria universal", "2025", "5to A");

        Nota nota = new Nota(
                3,
                8.5,
                alumno,
                curso
        );
        when(notaService.save(any(Nota.class))).thenReturn(nota);

        mockMvc.perform(post("/api/notas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "id": 3,
                                        "calificacion": 8.5,
                                        "alumno": {
                                            "id": 1,
                                            "nombre": "Pepe",
                                            "apellido": "Zapata",
                                            "fechaNacimiento": "2005-06-10T00:00:00.000+00:00",
                                            "direccion": "Av. La planicie",
                                            "telefono": "9214234381",
                                            "email": "pepe@mail.com",
                                            "fechaRegistro": "2024-04-20T00:00:00.000+00:00",
                                            "activo": true
                                        },
                                        "curso": {
                                            "id": 4,
                                            "nombre": "Historia universal",
                                            "anio": "2025",
                                            "seccion": "5to A"
                                        }
                                    }
                                """))
                .andExpect(status().isOk())
                .andDo(document("post--nota"));
    }
}
