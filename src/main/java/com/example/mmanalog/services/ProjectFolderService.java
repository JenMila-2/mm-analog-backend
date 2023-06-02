package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.repositories.UserRepository;
import com.example.mmanalog.repositories.PhotoRepository;
import com.example.mmanalog.repositories.ProjectFolderRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProjectFolderService {

    private final ProjectFolderRepository projectFolderRepository;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public ProjectFolderService(ProjectFolderRepository projectFolderRepository, PhotoRepository photoRepository, UserRepository userRepository) {
        this.projectFolderRepository = projectFolderRepository;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public List<ProjectFolderDto> getProjectFolders() {
        List<ProjectFolder> projectFolders = projectFolderRepository.findAll();
        List<ProjectFolderDto> projectFolderDtos = new ArrayList<>();

        for (ProjectFolder projectFolder : projectFolders) {
            projectFolderDtos.add(transferProjectFolderToDto(projectFolder));
        }
        return projectFolderDtos;
    }

    public ProjectFolderDto getProjectFolderById(Long id) {
        Optional<ProjectFolder> folderOptional = projectFolderRepository.findById(id);
        if (folderOptional.isPresent()) {
            ProjectFolder projectFolder = folderOptional.get();
            return transferProjectFolderToDto(projectFolder);
        } else {
            throw new RecordNotFoundException("No project folder found with id: " + id);
        }
    }

    public ProjectFolderDto addProjectFolder(ProjectFolderInputDto dtoProjectFolder) {

        ProjectFolder folder = transferToProjectFolder(dtoProjectFolder);
        projectFolderRepository.save(folder);

        return transferProjectFolderToDto(folder);
    }

    public void deleteProjectFolder(@RequestBody Long id) {
        projectFolderRepository.deleteById(id);
    }

    public ProjectFolderDto updateProjectFolder(Long id, ProjectFolderInputDto inputDtoProjectFolder) {

        if (projectFolderRepository.findById(id).isPresent()) {

            ProjectFolder projectFolder = projectFolderRepository.findById(id).get();

            ProjectFolder projectFolder1 = transferToProjectFolder(inputDtoProjectFolder);
            projectFolder1.setId(projectFolder.getId());

            projectFolderRepository.save(projectFolder1);

            return transferProjectFolderToDto(projectFolder1);

        } else {
            throw new RecordNotFoundException("No photo folder found with id: " + id);
        }
    }

    public ProjectFolder transferToProjectFolder(ProjectFolderInputDto projectFolderInputDto) {
        ProjectFolder folder = new ProjectFolder();

        folder.setId(projectFolderInputDto.getId());
        folder.setProjectTitle(projectFolderInputDto.getProjectTitle());
        folder.setProjectNote(projectFolderInputDto.getProjectNote());

        return folder;
    }

    public ProjectFolderDto transferProjectFolderToDto(ProjectFolder projectFolder) {
        ProjectFolderDto projectFolderDto = new ProjectFolderDto();

        projectFolderDto.setId(projectFolder.getId());
        projectFolderDto.setProjectTitle(projectFolder.getProjectTitle());
        projectFolderDto.setProjectNote(projectFolder.getProjectNote());
        projectFolderDto.setUser(projectFolder.getUser());

        return projectFolderDto;
    }

    // *** Methods related to the relationship between entities ***
    public ProjectFolderDto assignFolderToUser(Long id, Long userId) {
        Optional<ProjectFolder> projectFolderOptional = projectFolderRepository.findById(id);
        Optional<User> userOptional = userRepository.findById(userId);

        if (projectFolderOptional.isPresent() && userOptional.isPresent()) {
            ProjectFolder projectFolder = projectFolderOptional.get();
            User user = userOptional.get();

            projectFolder.setUser(user);
            projectFolderRepository.save(projectFolder);

            return transferProjectFolderToDto(projectFolder);
        } else {
            throw new RecordNotFoundException("No project folder or user found.");
        }
    }
}

