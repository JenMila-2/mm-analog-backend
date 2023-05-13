package com.example.mmanalog.services;

import com.example.mmanalog.dtos.ProjectFolderDto;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.repositories.ProjectFolderRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectFolderService {

    private final ProjectFolderRepository projectFolderRepository;

    public ProjectFolderService(ProjectFolderRepository projectFolderRepository) {
        this.projectFolderRepository = projectFolderRepository;
    }

    public List<ProjectFolderDto> getFolders() {
        Iterable<ProjectFolder> projectFolders = projectFolderRepository.findAll();
        List<ProjectFolderDto> projectFolderDtos = new ArrayList<>();

        for (ProjectFolder projectFolder : projectFolders) {
            projectFolderDtos.add(transferProjectFolderToDto(projectFolder));
        }
        return projectFolderDtos;
    }

    public ProjectFolderDto getFolderById(Long id) {
        Optional<ProjectFolder> folderOptional = projectFolderRepository.findById(id);
        if (folderOptional.isPresent()) {
            ProjectFolder projectFolder = folderOptional.get();
            return transferProjectFolderToDto(projectFolder);
        } else {
            throw new RecordNotFoundException("No project folder found with this id: " + id);
        }
    }

    public ProjectFolderDto addProjectFolder(ProjectFolderDto dtoProjectFolder) {

        ProjectFolder folder = transferToProjectFolder(dtoProjectFolder);
        projectFolderRepository.save(folder);

        return transferProjectFolderToDto(folder);
    }

    public void deleteProjectFolder(@RequestBody Long id) {
        projectFolderRepository.deleteById(id);
    }

    public ProjectFolderDto updateProjectFolder(Long id, ProjectFolderDto newProjectFolder) {
        Optional<ProjectFolder> folderOptional = projectFolderRepository.findById(id);
        if (folderOptional.isPresent()) {
            ProjectFolder folder = folderOptional.get();

            folder.setProjectTitle(newProjectFolder.getProjectTitle());
            folder.setProjectNotes(newProjectFolder.getProjectNotes());

            ProjectFolder returnFolder = projectFolderRepository.save(folder);

            return transferProjectFolderToDto(returnFolder);

        } else {
            throw new RecordNotFoundException("No project folder found with this id: " + id);
        }
    }

    public ProjectFolder transferToProjectFolder(ProjectFolderDto projectFolderDto) {

        var folder = new ProjectFolder();

        folder.setFolderId(projectFolderDto.getFolderId());
        folder.setProjectTitle(projectFolderDto.getProjectTitle());
        folder.setProjectNotes(projectFolderDto.getProjectNotes());

        return folder;
    }

    public ProjectFolderDto transferProjectFolderToDto(ProjectFolder projectFolder) {
        ProjectFolderDto projectFolderDto = new ProjectFolderDto();

        projectFolderDto.folderId = projectFolder.getFolderId();
        projectFolderDto.projectTitle = projectFolder.getProjectTitle();
        projectFolderDto.projectNotes = projectFolder.getProjectNotes();

        return projectFolderDto;
    }
}
