package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.InputDtos.FilmDevelopmentLogInputDto;
import com.example.mmanalog.dtos.InputDtos.PhotoLogInputDto;
import com.example.mmanalog.models.PhotoLog;
import com.example.mmanalog.repositories.PhotoLogRepository;
import com.example.mmanalog.services.PhotoLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class PhotoLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PhotoLogRepository photoLogRepository;
    @Autowired
    PhotoLogService photoLogService;

    PhotoLog photoLog1;
    PhotoLog photoLog2;

    PhotoLogInputDto photoLogInputDto1;
    PhotoLogInputDto photoLogInputDto2;
    PhotoLogInputDto photoLogInputDto3;

    @BeforeEach
    void setUp() {
        if (photoLogRepository.count() > 0) {
            photoLogRepository.deleteAll();
        }

        photoLog1 = new PhotoLog(1L, "Title 1", "Camera 1", "Kodak Gold 200", "35mm", 200, "f/5.6", "1/60", "+1", LocalDate.parse("2023-02-01"), "At home lab", "Notes 1", null, null);
        photoLog2 = new PhotoLog(2L, "Title 2", "Camera 2", "Kodak Ultra Max", "35mm", 400, "f/5.6", "1/60", "+1", LocalDate.parse("2023-02-01"), "At home lab", "Notes 2", null, null);

        photoLogRepository.save(photoLog1);
        photoLogRepository.save(photoLog2);

        photoLogInputDto1 = new PhotoLogInputDto(photoLog1.getId(), "Title 1", "Camera 1", "Kodak Gold 200", "35mm", 200, "f/5.6", "1/60", "+1", LocalDate.parse("2023-02-01"), "At home lab", "Notes 1", null);
        photoLogInputDto2 = new PhotoLogInputDto(photoLog2.getId(), "Title 2", "Camera 2", "Kodak Ultra Max", "35mm", 400, "f/5.6", "1/60", "+1", LocalDate.parse("2023-02-01"), "At home lab", "Notes 2", null);
        photoLogInputDto3 = new PhotoLogInputDto(3L, "Title 3", "Camera 3", "Kodak Gold 200", "35mm", 200, "f/5.6", "1/60", "+1", LocalDate.parse("2023-02-01"), "At home lab", "Notes 3", null);
    }

    @Test
    void getPhotoLogs() throws Exception {

        mockMvc.perform(get("/photologs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(photoLog1.getId().toString()))
                .andExpect(jsonPath("$[0].photoTitle").value("Title 1"))
                .andExpect(jsonPath("$[0].camera").value("Camera 1"))
                .andExpect(jsonPath("$[0].filmStock").value("Kodak Gold 200"))
                .andExpect(jsonPath("$[0].filmFormat").value("35mm"))
                .andExpect(jsonPath("$[0].shotAtIso").value(200))
                .andExpect(jsonPath("$[0].aperture").value("f/5.6"))
                .andExpect(jsonPath("$[0].shutterSpeed").value("1/60"))
                .andExpect(jsonPath("$[0].exposureCompensation").value("+1"))
                .andExpect(jsonPath("$[0].dateTaken").value("2023-02-01"))
                .andExpect(jsonPath("$[0].developedByLab").value("At home lab"))
                .andExpect(jsonPath("$[0].notes").value("Notes 1"))
                .andExpect(jsonPath("$[1].id").value(photoLog2.getId().toString()))
                .andExpect(jsonPath("$[1].photoTitle").value("Title 2"))
                .andExpect(jsonPath("$[1].camera").value("Camera 2"))
                .andExpect(jsonPath("$[1].filmStock").value("Kodak Ultra Max"))
                .andExpect(jsonPath("$[1].filmFormat").value("35mm"))
                .andExpect(jsonPath("$[1].shotAtIso").value(400))
                .andExpect(jsonPath("$[1].aperture").value("f/5.6"))
                .andExpect(jsonPath("$[1].shutterSpeed").value("1/60"))
                .andExpect(jsonPath("$[1].exposureCompensation").value("+1"))
                .andExpect(jsonPath("$[1].dateTaken").value("2023-02-01"))
                .andExpect(jsonPath("$[1].developedByLab").value("At home lab"))
                .andExpect(jsonPath("$[1].notes").value("Notes 2"));
    }

    @Test
    void getByPhotoLogFilmStockTest() throws Exception {
        String filmStock = "Kodak Gold 200";

        mockMvc.perform(get("/photologs/film_stock/{film_stock}", filmStock))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].filmStock").value(filmStock));
    }

    @Test
    void getByPhotoLogFilmFormatTest() throws Exception {
        String filmFormat = "35mm";

        mockMvc.perform(get("/photologs/film_format/{film_format}", filmFormat))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].filmFormat").value(filmFormat))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].filmFormat").value(filmFormat));
    }

    @Test
    //@Disabled
    void createPhotoLogTest() throws Exception {

        mockMvc.perform(post("/photologs/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(photoLogInputDto3)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(3L))
                .andExpect(jsonPath("photoTitle").value("Title 3"))
                .andExpect(jsonPath("camera").value("Camera 3"))
                .andExpect(jsonPath("filmStock").value("Kodak Gold 200"))
                .andExpect(jsonPath("filmFormat").value("35mm"))
                .andExpect(jsonPath("shotAtIso").value(200))
                .andExpect(jsonPath("aperture").value("f/5.6"))
                .andExpect(jsonPath("shutterSpeed").value("1/60"))
                .andExpect(jsonPath("exposureCompensation").value("+1"))
                .andExpect(jsonPath("dateTaken").value("2023-02-01"))
                .andExpect(jsonPath("developedByLab").value("At home lab"))
                .andExpect(jsonPath("notes").value("Notes 3"));
    }

    /*@Test
    //@Disabled
    void updatePhotoLogTest() throws Exception {
        Long photoLogId = 1L;
        PhotoLogInputDto updatedPhotoLog = new PhotoLogInputDto(photoLogId, "Updated Title", "Updated Camera",
                "Kodak Gold 200", "35mm", 200, "f/5.6", "1/60", "+1",
                LocalDate.parse("2023-02-01"), "At home lab", "Updated Notes", null);

        mockMvc.perform(MockMvcRequestBuilders.put("/photologs/{id}", photoLogId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedPhotoLog)))
                .andExpect(status().isOk());
    }*/

    @Test
    void deletePhotoLogTest() throws Exception {
        mockMvc.perform(delete("/photologs/" + photoLog2.getId().toString()))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final PhotoLogInputDto obj) {
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