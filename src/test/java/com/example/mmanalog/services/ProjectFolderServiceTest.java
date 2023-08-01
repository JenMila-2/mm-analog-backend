package com.example.mmanalog.services;

import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.ProjectFolderRepository;
import com.example.mmanalog.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectFolderServiceTest {

    @Mock
    ProjectFolderRepository projectFolderRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ProjectFolderService projectFolderService;

    @Captor
    ArgumentCaptor<ProjectFolder> captor;

    ProjectFolder projectFolder1;
    ProjectFolder projectFolder2;
    User user1;
    User user2;
    Image image1;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("UsernameTest1");
        user1.setName("Barry Allen");
        user1.setEmail("barry@mail.com");
        user1.setPassword("UserPassword1");
        user1.setEnabled(true);

        user2 = new User();
        user2.setUsername("UsernameTest2");
        user2.setName("Donna Troy");
        user2.setEmail("donna@mail.com");
        user2.setPassword("UserPassword2");
        user2.setEnabled(true);

        projectFolder1 = new ProjectFolder();
        projectFolder1.setId(1L);
        projectFolder1.setProjectTitle("Project 1");
        projectFolder1.setProjectConcept("This is a concept for project 1");

        projectFolder2 = new ProjectFolder();
        projectFolder2.setId(2L);
        projectFolder2.setProjectTitle("Project 2");
        projectFolder2.setProjectConcept("This is a concept for project 2");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProjectFolders() {

        //Arrange
        when(projectFolderRepository.findAll()).thenReturn(List.of(projectFolder1, projectFolder2));

        //Act
        List<ProjectFolder> projectFolders = projectFolderRepository.findAll();
        List<ProjectFolderDto> folderDtos = projectFolderService.getProjectFolders();

        //Assert
        assertEquals(projectFolders.get(0).getProjectTitle(), folderDtos.get(0).getProjectTitle());
        assertEquals(projectFolders.get(0).getProjectConcept(), folderDtos.get(0).getProjectConcept());
    }

    @Test
    void getProjectFolder() {
        //Arrange
        when(projectFolderRepository.findById(1L)).thenReturn(Optional.of(projectFolder1));

        //Act
        ProjectFolderDto projectFolderDto = projectFolderService.getProjectFolder(1L);

        //Assert
        assertEquals(projectFolder1.getProjectTitle(), projectFolderDto.getProjectTitle());
        assertEquals(projectFolder1.getProjectConcept(), projectFolderDto.getProjectConcept());
    }

    @Test
    void getProjectFolderThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> projectFolderService.getProjectFolder(null));
    }

    @Test
    void createProjectFolder() {
        //Arrange
        ProjectFolderInputDto projectFolderInputDto = new ProjectFolderInputDto();
        projectFolderInputDto.setProjectTitle("Project 3");
        projectFolderInputDto.setProjectConcept("Concept for project 3");

        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setProjectTitle("Project 3");
        projectFolder.setProjectConcept("Concept for project 3");

        when(projectFolderRepository.save(any(ProjectFolder.class))).thenReturn(projectFolder);

        //Act
        ProjectFolderDto resultProjectFolderDto = projectFolderService.createProjectFolder(projectFolderInputDto);

        //Assert
        verify(projectFolderRepository).save(captor.capture());
        ProjectFolder savedProjectFolder = captor.getValue();

        assertEquals(projectFolderInputDto.getProjectTitle(), savedProjectFolder.getProjectTitle());
        assertEquals(projectFolderInputDto.getProjectConcept(), savedProjectFolder.getProjectConcept());

        assertEquals(projectFolder.getProjectTitle(), resultProjectFolderDto.getProjectTitle());
        assertEquals(projectFolder.getProjectConcept(), resultProjectFolderDto.getProjectConcept());
    }

  @Test
    void updateProjectFolder() {
        // Arrange
        Long projectId = 2L;
        ProjectFolderDto updatedProjectFolder = new ProjectFolderDto();
        updatedProjectFolder.setId(projectId);
        updatedProjectFolder.setProjectTitle("Project 2 update");
        updatedProjectFolder.setProjectConcept("Updated concept of project 2");

        ProjectFolder storedProjectFolder = new ProjectFolder();
        storedProjectFolder.setId(projectId);
        storedProjectFolder.setProjectTitle("Old project title");
        storedProjectFolder.setProjectConcept("Old project concept");

        when(projectFolderRepository.existsById(projectId)).thenReturn(true);
        when(projectFolderRepository.findById(projectId)).thenReturn(Optional.of(storedProjectFolder));
        when(projectFolderRepository.save(any(ProjectFolder.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        projectFolderService.updateProjectFolder(projectId, updatedProjectFolder);

        // Assert
        verify(projectFolderRepository, times(1)).existsById(projectId);
        verify(projectFolderRepository, times(1)).findById(projectId);
        verify(projectFolderRepository, times(1)).save(any(ProjectFolder.class));

        // Verify that the storedProjectFolder is updated
        assertEquals(updatedProjectFolder.getProjectTitle(), storedProjectFolder.getProjectTitle());
        assertEquals(updatedProjectFolder.getProjectConcept(), storedProjectFolder.getProjectConcept());
    }

    @Test
    void updateProjectFolderThrowsExceptionTest() {
        // Arrange
        Long nonExistingId = 888L;
        ProjectFolderDto updatedProjectFolder = new ProjectFolderDto();
        updatedProjectFolder.setProjectTitle("updated project folder");

        when(projectFolderRepository.existsById(nonExistingId)).thenReturn(false);

        // Act and Assert
        assertThrows(RecordNotFoundException.class, () -> projectFolderService.updateProjectFolder(nonExistingId, updatedProjectFolder));

        // Verify that the repository method was called
        verify(projectFolderRepository, times(1)).existsById(nonExistingId);
    }

    @Test
    void deleteProjectFolder() {
        //Act
        projectFolderService.deleteProjectFolder(2L);

        //Assert
        verify(projectFolderRepository).deleteById(2L);

        assertFalse(projectFolderRepository.findById(projectFolder2.getId()).isPresent());
    }

    @Test
    void createFolderForUserTest() {
        //Arrange
        String username = "UsernameTest3";
        ProjectFolderInputDto projectFolderInputDto = new ProjectFolderInputDto();
        projectFolderInputDto.setProjectTitle("New Project Folder 3");

        User user = new User();
        user.setUsername(username);

        when(userRepository.findById(username)).thenReturn(Optional.of(user));

        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setUser(user);
        projectFolder.setProjectTitle("New Project Folder 3");

        when(projectFolderRepository.save(any(ProjectFolder.class))).thenReturn(projectFolder);

        //Act
        ProjectFolderDto resultProjectFolderDto = projectFolderService.createFolderForUser(username, projectFolderInputDto);

        //Assert
        verify(userRepository).findById(username);
        verify(projectFolderRepository).save(captor.capture());
        ProjectFolder savedProjectFolder = captor.getValue();

        assertEquals(user, savedProjectFolder.getUser());
        assertEquals(projectFolderInputDto.getProjectTitle(), savedProjectFolder.getProjectTitle());

        assertEquals(projectFolder.getProjectTitle(), resultProjectFolderDto.getProjectTitle());
    }

    @Test
    void createFolderForUse_userNotFound() {
        //Arrange
        String username = "NonExistingUser";
        ProjectFolderInputDto projectFolderInputDto = new ProjectFolderInputDto();
        projectFolderInputDto.setProjectTitle("New Project Folder 3");

        when(userRepository.findById(username)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(UserNotFoundException.class, () -> projectFolderService.createFolderForUser(username, projectFolderInputDto));

        verify(userRepository).findById(username);
        verifyNoInteractions(projectFolderRepository);
    }
}