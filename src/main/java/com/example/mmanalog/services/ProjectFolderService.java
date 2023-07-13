package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.exceptions.UserNotFoundException;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.User;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ProjectFolderDto updateProjectFolder(Long id, ProjectFolderInputDto updatedProjectFolder) {

        if (projectFolderRepository.findById(id).isPresent()) {
            ProjectFolder projectFolder = projectFolderRepository.findById(id).get();

            ProjectFolder projectFolder1 = transferToProjectFolder(updatedProjectFolder);
            projectFolder1.setId(projectFolder.getId());
            projectFolderRepository.save(projectFolder1);

            return transferProjectFolderToDto(projectFolder1);
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + id);
        }
    }

    public void deleteProjectFolder(@RequestBody Long id) {
        projectFolderRepository.deleteById(id);
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

    public ProjectFolderDto assignImageToFolder(Long folderId, Long imageId) {
        Optional<ProjectFolder> optionalProjectFolder = projectFolderRepository.findById(folderId);
        Optional<Image> optionalImage = imageRepository.findById(imageId);

        if (optionalProjectFolder.isPresent() && optionalImage.isPresent()) {
            ProjectFolder projectFolder = optionalProjectFolder.get();
            Image image = optionalImage.get();

            image.setProjectFolder(projectFolder);
            projectFolder.getImages().add(image);

            projectFolderRepository.save(projectFolder);

            return transferProjectFolderToDto(projectFolder);
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + folderId + " or no image found with id: " + imageId);
        }
    }

    public byte[] getFolderImage(Long folderId, Long imageId) {
        Optional<ProjectFolder> optionalProjectFolder = projectFolderRepository.findById(folderId);

        if (optionalProjectFolder.isPresent()) {
            ProjectFolder projectFolder = optionalProjectFolder.get();
            List<Image> images = projectFolder.getImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getId().equals(imageId))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                return image.getImage();
            } else {
                throw new RecordNotFoundException("No image found with id: " + imageId);
            }
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + folderId);
        }
    }

    public void deleteFolderImage(Long folderId, Long imageId) {
        Optional<ProjectFolder> optionalProjectFolder = projectFolderRepository.findById(folderId);

        if (optionalProjectFolder.isPresent()) {
            ProjectFolder projectFolder = optionalProjectFolder.get();
            List<Image> images = projectFolder.getImages();

            Optional<Image> optionalImage = images.stream()
                    .filter(image -> image.getId().equals(imageId))
                    .findFirst();

            if (optionalImage.isPresent()) {
                Image image = optionalImage.get();
                images.remove(image);
                imageRepository.delete(image);
                projectFolderRepository.save(projectFolder);
            } else {
                throw new RecordNotFoundException("No image found with id: " + imageId);
            }
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + folderId);
        }
    }

    /* Method below only returns the image data and not the actual images */
    public List<byte[]> getAllFolderImages(Long folderId) {
        Optional<ProjectFolder> optionalProjectFolder = projectFolderRepository.findById(folderId);

        if (optionalProjectFolder.isPresent()) {
            ProjectFolder projectFolder = optionalProjectFolder.get();
            List<Image> images = projectFolder.getImages();

            List<byte[]> imageList = new ArrayList<>();
            for (Image image : images) {
                imageList.add(image.getImage());
            }
            return imageList;
        } else {
            throw new RecordNotFoundException("N0 folder found with id: " + folderId);
        }
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
}

