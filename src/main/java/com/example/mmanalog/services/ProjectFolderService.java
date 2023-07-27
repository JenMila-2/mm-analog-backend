package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.User;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProjectFolderService {

    private final ProjectFolderRepository projectFolderRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public ProjectFolderService(ProjectFolderRepository projectFolderRepository, UserRepository userRepository, ImageRepository imageRepository) {
        this.projectFolderRepository = projectFolderRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    public List<ProjectFolderDto> getProjectFolders() {
        List<ProjectFolder> projectFolders = projectFolderRepository.findAll();
        List<ProjectFolderDto> projectFolderList = new ArrayList<>();

        for (ProjectFolder projectFolder : projectFolders) {
            projectFolderList.add(transferProjectFolderToDto(projectFolder));
        }
        return projectFolderList;
    }

    public ProjectFolderDto getProjectFolder(Long id) {
        Optional<ProjectFolder> folderOptional = projectFolderRepository.findById(id);
        if (folderOptional.isPresent()) {
            ProjectFolder projectFolder = folderOptional.get();
            return transferProjectFolderToDto(projectFolder);
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + id);
        }
    }

    public List<ProjectFolderDto> getAllProjectFoldersByUser(User user) {
        List<ProjectFolder> projectFolderList = projectFolderRepository.findProjectFolderByUser(user);
        List<ProjectFolderDto> projectFolderDtoList = new ArrayList<>();
        for(ProjectFolder projectFolder : projectFolderList) {
            ProjectFolderDto dto = transferProjectFolderToDto(projectFolder);
            projectFolderDtoList.add(dto);
        }
        return projectFolderDtoList;
    }

    public ProjectFolderDto createProjectFolder(ProjectFolderInputDto dtoInputProjectFolder) {
        ProjectFolder folder = transferToProjectFolder(dtoInputProjectFolder);
        projectFolderRepository.save(folder);

        return transferProjectFolderToDto(folder);
    }

    public void updateProjectFolder(Long id, ProjectFolderDto updatedProjectFolder) {
        if (!projectFolderRepository.existsById(id)) {
            throw new RecordNotFoundException("No project folder found with id: " + id);
        }
        ProjectFolder storedProjectFolder = projectFolderRepository.findById(id).orElse(null);
            storedProjectFolder.setProjectTitle(updatedProjectFolder.getProjectTitle());
            storedProjectFolder.setProjectConcept(updatedProjectFolder.getProjectConcept());
            storedProjectFolder.setUser(updatedProjectFolder.getUser());
            projectFolderRepository.save(storedProjectFolder);
    }

    public void deleteProjectFolder(@RequestBody Long id) {
        projectFolderRepository.deleteById(id);
    }

    //*---------------------------------Transfers---------------------------------*//

    public ProjectFolder transferToProjectFolder(ProjectFolderInputDto projectFolderInputDto) {
        ProjectFolder folder = new ProjectFolder();

        folder.setId(projectFolderInputDto.getId());
        folder.setProjectTitle(projectFolderInputDto.getProjectTitle());
        folder.setProjectConcept(projectFolderInputDto.getProjectConcept());

        return folder;
    }

    public ProjectFolderDto transferProjectFolderToDto(ProjectFolder projectFolder) {
        ProjectFolderDto projectFolderDto = new ProjectFolderDto();

        projectFolderDto.setId(projectFolder.getId());
        projectFolderDto.setProjectTitle(projectFolder.getProjectTitle());
        projectFolderDto.setProjectConcept(projectFolder.getProjectConcept());
        projectFolderDto.setUser(projectFolder.getUser());

        return projectFolderDto;
    }

    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    public ProjectFolderDto createFolderForUser(String username, ProjectFolderInputDto folderInputDto) {
        Optional<User> userOptional = userRepository.findById(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            ProjectFolder projectFolder = new ProjectFolder();
            projectFolder.setUser(user);
            projectFolder.setProjectTitle(folderInputDto.getProjectTitle());
            projectFolder.setProjectConcept(folderInputDto.getProjectConcept());

            projectFolderRepository.save(projectFolder);

            return transferProjectFolderToDto(projectFolder);
        } else {
            throw new UserNotFoundException("No user found with username: " + username);
        }
    }

    public ProjectFolderDto uploadImageToFolder(Long folderId, MultipartFile file) throws IOException {
        Optional<ProjectFolder> optionalProjectFolder = projectFolderRepository.findById(folderId);

        if (optionalProjectFolder.isPresent()) {
            ProjectFolder projectFolder = optionalProjectFolder.get();

            Image image = Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .image(ImageUtility.compressImage(file.getBytes()))
                    .projectFolder(projectFolder)
                    .build();

            imageRepository.save(image);

            return transferProjectFolderToDto(projectFolder);
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + folderId);
        }
    }

    public byte[] getFolderImageByName(Long folderId, String imageName) {
        Optional<ProjectFolder> optionalProjectFolder = projectFolderRepository.findById(folderId);

        if (optionalProjectFolder.isPresent()) {
            ProjectFolder projectFolder = optionalProjectFolder.get();
            List<Image> images = projectFolder.getImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getName().equals(imageName))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                return ImageUtility.decompressImage(image.getImage());
            } else {
                throw new RecordNotFoundException("No image found with name: " + imageName + " in project folder with id: " + folderId);
            }
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + folderId);
        }
    }

    public void deleteFolderImageByName(Long folderId, String imageName) {
        Optional<ProjectFolder> optionalProjectFolder = projectFolderRepository.findById(folderId);

        if (optionalProjectFolder.isPresent()) {
            ProjectFolder projectFolder = optionalProjectFolder.get();
            List<Image> images = projectFolder.getImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getName().equals(imageName))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                images.remove(image);
                imageRepository.delete(image);
            } else {
                throw new RecordNotFoundException("No image found with name: " + imageName);
            }
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + folderId);
        }
    }
}

