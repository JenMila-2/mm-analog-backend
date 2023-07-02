package com.example.mmanalog.integrationtests;

import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.models.PhotoLog;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.repositories.PhotoLogRepository;
import com.example.mmanalog.repositories.ProjectFolderRepository;
import com.example.mmanalog.services.PhotoLogService;
import com.example.mmanalog.services.ProjectFolderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectFolderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProjectFolderRepository projectFolderRepository;

    @Autowired
    ProjectFolderService projectFolderService;

    @Autowired
    PhotoLogRepository photoLogRepository;

    @Autowired
    PhotoLogService photoLogService;

    ProjectFolder projectFolder1;
    ProjectFolder projectFolder2;
    ProjectFolderInputDto projectFolderInputDto1;
    ProjectFolderInputDto projectFolderInputDto2;
    ProjectFolderInputDto projectFolderInputDto3;
    PhotoLog photoLog1;
    PhotoLog photoLog2;

    @BeforeEach
    void setUp() {
        projectFolderRepository.deleteAll();
        photoLogRepository.deleteAll();

        projectFolder1 = new ProjectFolder(1L,"Project 1", "Concept for project 1", null,null, null);
        projectFolder2 = new ProjectFolder(2L, "Project 2", "Concept for project 2", null, null, null);

        projectFolderRepository.save(projectFolder1);
        projectFolderRepository.save(projectFolder2);

        projectFolderInputDto1 = new ProjectFolderInputDto(projectFolderInputDto1.getId(), "Project 1", "Concept for project 1", null, null, null);
        projectFolderInputDto2 = new ProjectFolderInputDto(projectFolderInputDto2.getId(), "Project 2", "Concept for project 2", null, null, null);
        projectFolderInputDto3 = new ProjectFolderInputDto(projectFolderInputDto3.getId(), "Project folder 3", "Concept for project 3", null, null, null);
    }

    @Test
    @Disabled
    void getProjectFolders() throws Exception {

        mockMvc.perform(get("/projectfolders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(projectFolder1.getId().toString()))
                .andExpect(jsonPath("$[0].projectTitle").value("Project 1"))
                .andExpect(jsonPath("$[0].projectConcept").value("Concept for project 1"))
                .andExpect(jsonPath("$[1].id").value(projectFolder2.getId().toString()))
                .andExpect(jsonPath("$[1].projectTitle").value("Project 2"))
                .andExpect(jsonPath("$[1].projectConcept").value("Concept for project 2"));
    }

    @Test
    @Disabled
    void getProjectFolder() {
    }

    @Test
    @Disabled
    void createProjectFolder() {
    }

    @Test
    @Disabled
    void updateProjectFolder() {
    }

    @Test
    @Disabled
    void deleteProjectFolder() {
    }

    @Test
    @Disabled
    void createFolderForUser() {
    }

    @Test
    @Disabled
    void assignImageToFolder() {
    }

    @Test
    @Disabled
    void getFolderImage() {
    }

    @Test
    @Disabled
    void deleteFolderImage() {
    }

    @Test
    @Disabled
    void getAllFolderImages() {
    }
}