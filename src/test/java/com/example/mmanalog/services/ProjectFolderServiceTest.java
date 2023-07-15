package com.example.mmanalog.services;

import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.models.User;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.repositories.ImageRepository;
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

    @Mock
    ImageRepository imageRepository;

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

    @Test
    void assignImageToFolderTest() {
        // Arrange
        Long folderId = 1L;
        Long imageId = 2L;

        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setId(folderId);

        Image imageMock = Image.builder()
                .id(imageId)
                .build();

        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.of(projectFolder));
        when(imageRepository.findById(imageId)).thenReturn(Optional.of(imageMock));
        when(projectFolderRepository.save(any(ProjectFolder.class))).thenReturn(projectFolder);

        // Act
        ProjectFolderDto result = projectFolderService.assignImageToFolder(folderId, imageId);

        // Assert
        verify(projectFolderRepository).findById(folderId);
        verify(imageRepository).findById(imageId);
        verify(projectFolderRepository).save(projectFolder);

        assertEquals(folderId, imageMock.getProjectFolder().getId());
        assertTrue(projectFolder.getImages().contains(imageMock));
        assertEquals(projectFolder.getId(), result.getId());
    }

    @Test
    void getFolderImageTest() {
        // Arrange
        Long folderId = 1L;
        Long imageId = 1L;

        // Create a sample image
        Image image = new Image();
        image.setId(imageId);
        image.setImage(new byte[] { 0x01, 0x02, 0x03 });

        // Create a sample project folder with the image
        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setId(folderId);
        List<Image> images = new ArrayList<>();
        images.add(image);
        projectFolder.setImages(images);

        // Mock the project folder repository
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.of(projectFolder));

        // Act
        byte[] result = projectFolderService.getFolderImage(folderId, imageId);

        // Assert
        assertArrayEquals(new byte[] { 0x01, 0x02, 0x03 }, result);
    }

    @Test
    public void getFolderImage_NonexistentFolderId_ThrowsRecordNotFoundException() {
        // Arrange
        Long folderId = 999L;
        Long imageId = 1L;

        // Mock the project folder repository to return an empty optional
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class,
                () -> projectFolderService.getFolderImage(folderId, imageId));
    }

    @Test
    public void getFolderImage_NonexistentImageId_ThrowsRecordNotFoundException() {
        // Arrange
        Long folderId = 1L;
        Long imageId = 888L;

        // Create a sample project folder without any images
        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setId(folderId);
        projectFolder.setImages(new ArrayList<>());

        // Mock the project folder repository
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.of(projectFolder));

        // Act and Assert
        assertThrows(RecordNotFoundException.class,
                () -> projectFolderService.getFolderImage(folderId, imageId));
    }

    @Test
    void deleteFolderExistingImage() {
        // Arrange
        Long folderId = 1L;
        Long imageId = 1L;

        // Create a sample image
        Image image = new Image();
        image.setId(imageId);

        // Create a sample project folder with the image
        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setId(folderId);
        List<Image> images = new ArrayList<>();
        images.add(image);
        projectFolder.setImages(images);

        // Mock the project folder repository
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.of(projectFolder));

        // Act
        projectFolderService.deleteFolderImage(folderId, imageId);

        // Assert
        assertTrue(images.isEmpty());
        verify(imageRepository, times(1)).delete(image);
        verify(projectFolderRepository, times(1)).save(projectFolder);
    }

    @Test
    public void deleteFolderImage_NonexistentFolderId_ThrowsRecordNotFoundExceptionTest() {
        // Arrange
        Long folderId = 999L;
        Long imageId = 1L;

        // Mock the project folder repository to return an empty optional
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class,
                () -> projectFolderService.deleteFolderImage(folderId, imageId));
    }

    @Test
    public void deleteFolderImage_NonexistentImageId_ThrowsRecordNotFoundExceptionTest() {
        // Arrange
        Long folderId = 1L;
        Long imageId = 888L;

        // Create a sample project folder without any images
        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setId(folderId);
        projectFolder.setImages(new ArrayList<>());

        // Mock the project folder repository
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.of(projectFolder));

        // Act and Assert
        assertThrows(RecordNotFoundException.class,
                () -> projectFolderService.deleteFolderImage(folderId, imageId));
    }

    @Test
    void getAllFolderImagesExistingFolderTest() {
        // Arrange
        Long folderId = 1L;

        // Create a sample image list
        List<Image> images = new ArrayList<>();
        byte[] imageData1 = {0x01, 0x02, 0x03};
        byte[] imageData2 = {0x04, 0x05, 0x06};
        images.add(createImage(1L, imageData1));
        images.add(createImage(2L, imageData2));

        // Create a sample project folder with the image list
        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setId(folderId);
        projectFolder.setImages(images);

        // Mock the project folder repository
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.of(projectFolder));

        // Act
        List<byte[]> result = projectFolderService.getAllFolderImages(folderId);

        // Assert
        assertEquals(2, result.size());
        assertArrayEquals(imageData1, result.get(0));
        assertArrayEquals(imageData2, result.get(1));
    }

    @Test
    public void getAllFolderImages_NonexistentFolderId_ThrowsRecordNotFoundException() {
        // Arrange
        Long folderId = 999L;

        // Mock the project folder repository to return an empty optional
        when(projectFolderRepository.findById(folderId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RecordNotFoundException.class,
                () -> projectFolderService.getAllFolderImages(folderId));
    }

    // Helper method to create an Image instance
    private Image createImage(Long id, byte[] imageData) {
        Image image = new Image();
        image.setId(id);
        image.setImage(imageData);
        return image;
    }
}