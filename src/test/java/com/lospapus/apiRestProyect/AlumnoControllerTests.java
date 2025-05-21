package com.lospapus.apiRestProyect;

import com.lospapus.apiRestProyect.controller.AlumnoController;
import com.lospapus.apiRestProyect.model.Alumno;
import com.lospapus.apiRestProyect.service.AlumnoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlumnoController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class AlumnoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoService alumnoService;

    @Test
    void testGetALumnos() throws Exception {
        Date fechaNacimiento = new Date();
        Date fechaRegistro = new Date();

        List<Alumno> alumnos = List.of(new Alumno(1,
                "Juan",
                "Pérez",
                fechaNacimiento,
                "Calle Falsa 123",
                "123456789",
                "juan@example.com",
                fechaRegistro,
                true));
        when(alumnoService.findAll()).thenReturn(alumnos);

        mockMvc.perform(get("/api/alumnos"))
                .andExpect(status().isOk())
                .andDo(document("get-alumnos"));
    }

    @Test
    void testPostCurso() throws Exception {
        Date fechaNacimiento = new Date();
        Date fechaRegistro = new Date();

        Alumno alumno = new Alumno(
                1,
                "Juan",
                "Pérez",
                fechaNacimiento,
                "Calle Falsa 123",
                "123456789",
                "juan@example.com",
                fechaRegistro,
                true);

        when(alumnoService.save(any(Alumno.class))).thenReturn(alumno);

        mockMvc.perform(post("/api/alumnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": 1,
                                    "nombre": "Juan",
                                    "apellido": "Pérez",
                                    "fechaNacimiento": "2005-06-10T00:00:00.000+00:00",
                                    "direccion": "Av. Siempre Viva 123",
                                    "telefono": "123456789",
                                    "email": "juan@example.com",
                                    "fechaRegistro": "2024-04-20T00:00:00.000+00:00",
                                    "activo": true
                                }
                                """))
                .andExpect(status().isOk())
                .andDo(document("post-alumno"));
    }

    @Test
    void  testGetAlumnoById() throws Exception {
        Alumno alumno = new Alumno(1, "Asdy", "Valdivia", new Date(), "Av. Ignacio Merino", "9494970174", "asdy@hshd.com", new Date(), true);

        when(alumnoService.getById(1)).thenReturn(Optional.of(alumno));

        mockMvc.perform(get("/api/alumnos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAlumno() throws Exception {
        when(alumnoService.deleteById(1)).thenReturn(true);

        mockMvc.perform(delete("/api/alumnos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAlumno() throws Exception {
        Alumno alumno = new Alumno(1, "Nuevo", "Nombre", new Date(), "Calle", "123456", "nuevo@mail.com", new Date(), true);

        when(alumnoService.editById(eq(1), any(Alumno.class))).thenReturn(Optional.of(alumno));

        mockMvc.perform(put("/api/alumnos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "id": 1,
                        "nombre": "Josue",
                        "apellido": "Palacios",
                        "fechaNacimiento": "2000-01-01T00:00:00.000+00:00",
                        "direccion": "Av. Oni wan",
                        "telefono": "921821821",
                        "email": "josue@gmail.com",
                        "fechaRegistro": "2024-04-20T00:00:00.000+00:00",
                        "activo": true
                    }
                """))
                .andExpect(status().isOk());
    }
}
