package com.example.mmanalog.integrationtests;

import com.example.mmanalog.dtos.InputDtos.FilmDevelopmentLogInputDto;
import com.example.mmanalog.models.FilmDevelopmentLog;
import com.example.mmanalog.repositories.FilmDevelopmentLogRepository;
import com.example.mmanalog.services.FilmDevelopmentLogService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class FilmDevelopmentLogIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    FilmDevelopmentLogRepository filmDevelopmentLogRepository;
    @Autowired
    FilmDevelopmentLogService filmDevelopmentLogService;

    FilmDevelopmentLog filmDevelopmentLog1;
    FilmDevelopmentLog filmDevelopmentLog2;
    FilmDevelopmentLogInputDto filmDevelopmentLogInputDto1;
    FilmDevelopmentLogInputDto filmDevelopmentLogInputDto2;
    FilmDevelopmentLogInputDto filmDevelopmentLogInputDto3;

    @BeforeEach
    void setUp() {
        if (filmDevelopmentLogRepository.count() > 0) {
            filmDevelopmentLogRepository.deleteAll();
        }

        filmDevelopmentLog1 = new FilmDevelopmentLog(1L, "Roll 101 - Kodak Gold", "Project 1", "Minolta SRT101", "Kodak Gold 200", "35mm", 200, "C-41 Color", "Done", LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-15"), true, true, true, "Ontwikkelservice Foto Verweij Nijmegen", null);

        filmDevelopmentLog2 = new FilmDevelopmentLog(2L, "Roll 102 - Ilford Delta 3200", "Project 2", "Minolta SRT101", "Delta 3200", "35mm", 1000, "Black & White", "Done", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-20"), true, true, true, "Ontwikkelservice Foto Verweij Nijmegen", null);

        filmDevelopmentLogRepository.save(filmDevelopmentLog1);
        filmDevelopmentLogRepository.save(filmDevelopmentLog2);

        filmDevelopmentLogInputDto1 = new FilmDevelopmentLogInputDto(filmDevelopmentLog1.getId(), "Roll 101 - Kodak Gold", "Project 1", "Minolta SRT101", "Kodak Gold 20", "35mm", 200, "C-41 Color", "Done", LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-15"), true, true, true, "Ontwikkelservice Foto Verweij Nijmegen", null);

        filmDevelopmentLogInputDto2 = new FilmDevelopmentLogInputDto(filmDevelopmentLog1.getId(), "Roll 102 - Ilford Delta 3200", "Project 2", "Minolta SRT101", "Delta 3200", "35mm", 1000, "Black & White", "Done", LocalDate.parse("2023-05-01"), LocalDate.parse("2023-05-20"), true, true, true, "Ontwikkelservice Foto Verweij Nijmegen", null);

        filmDevelopmentLogInputDto3 = new FilmDevelopmentLogInputDto(3L, "Roll 103 - Ilford HP5 Plus", "Project 3", "Minolta Riva Zoom 115", "HP5 Plus", "35mm", 400, "Black & White", "Done", LocalDate.parse("2023-03-01"), LocalDate.parse("2023-04-20"), true, true, true, "Ontwikkelservice Foto Verweij Nijmegen", null);
    }

  @Test
    void getFilmDevelopmentLogsTest() throws Exception {

        mockMvc.perform(get("/filmdevelopmentlogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(filmDevelopmentLog1.getId().toString()))
                .andExpect(jsonPath("$[0].rollName").value("Roll 101 - Kodak Gold"))
                .andExpect(jsonPath("$[0].project").value("Project 1"))
                .andExpect(jsonPath("$[0].camera").value("Minolta SRT101"))
                .andExpect(jsonPath("$[0].stock").value("Kodak Gold 200"))
                .andExpect(jsonPath("$[0].format").value("35mm"))
                .andExpect(jsonPath("$[0].shotAtIso").value(200))
                .andExpect(jsonPath("$[0].developmentProcess").value("C-41 Color"))
                .andExpect(jsonPath("$[0].status").value("Done"))
                .andExpect(jsonPath("$[0].rollStarted").value("2023-04-01"))
                .andExpect(jsonPath("$[0].rollFinished").value("2023-04-15"))
                .andExpect(jsonPath("$[0].exposed").value(true))
                .andExpect(jsonPath("$[0].developed").value(true))
                .andExpect(jsonPath("$[0].scanned").value(true))
                .andExpect(jsonPath("$[0].developedByLab").value("Ontwikkelservice Foto Verweij Nijmegen"))
                .andExpect(jsonPath("$[1].id").value(filmDevelopmentLog2.getId().toString()))
                .andExpect(jsonPath("$[1].rollName").value("Roll 102 - Ilford Delta 3200"))
                .andExpect(jsonPath("$[1].project").value("Project 2"))
                .andExpect(jsonPath("$[1].camera").value("Minolta SRT101"))
                .andExpect(jsonPath("$[1].stock").value("Delta 3200"))
                .andExpect(jsonPath("$[1].format").value("35mm"))
                .andExpect(jsonPath("$[1].shotAtIso").value(1000))
                .andExpect(jsonPath("$[1].developmentProcess").value("Black & White"))
                .andExpect(jsonPath("$[1].status").value("Done"))
                .andExpect(jsonPath("$[1].rollStarted").value("2023-05-01"))
                .andExpect(jsonPath("$[1].rollFinished").value("2023-05-20"))
                .andExpect(jsonPath("$[1].exposed").value(true))
                .andExpect(jsonPath("$[1].developed").value(true))
                .andExpect(jsonPath("$[1].scanned").value(true))
                .andExpect(jsonPath("$[1].developedByLab").value("Ontwikkelservice Foto Verweij Nijmegen"));
    }

    @Test
    void createFilmDevelopmentLogTest() throws Exception {

        mockMvc.perform(post("/filmdevelopmentlogs/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmDevelopmentLogInputDto3)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(3L))
                .andExpect(jsonPath("rollName").value("Roll 103 - Ilford HP5 Plus"))
                .andExpect(jsonPath("project").value("Project 3"))
                .andExpect(jsonPath("camera").value("Minolta Riva Zoom 115"))
                .andExpect(jsonPath("stock").value("HP5 Plus"))
                .andExpect(jsonPath("format").value("35mm"))
                .andExpect(jsonPath("shotAtIso").value(400))
                .andExpect(jsonPath("developmentProcess").value("Black & White"))
                .andExpect(jsonPath("status").value("Done"))
                .andExpect(jsonPath("rollStarted").value("2023-03-01"))
                .andExpect(jsonPath("rollFinished").value("2023-04-20"))
                .andExpect(jsonPath("exposed").value(true))
                .andExpect(jsonPath("developed").value(true))
                .andExpect(jsonPath("scanned").value(true))
                .andExpect(jsonPath("developedByLab").value("Ontwikkelservice Foto Verweij Nijmegen"));
    }

   @Test
    void deleteFilmDevelopmentLogTest() throws Exception {
        mockMvc.perform(delete("/filmdevelopmentlogs/" + filmDevelopmentLog2.getId().toString()))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final FilmDevelopmentLogInputDto obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}