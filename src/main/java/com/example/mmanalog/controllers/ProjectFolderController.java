package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.repositories.FileUploadRepository;
import com.example.mmanalog.services.FileService;
import com.example.mmanalog.services.ProjectFolderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/projectfolders")
public class ProjectFolderController {

    private final ProjectFolderService projectFolderService;
    private final FileUploadRepository fileUploadRepository;
    private final FileController fileController;
    private final FileService fileService;

    public ProjectFolderController(ProjectFolderService projectFolderService, FileController fileController, FileService fileService, FileUploadRepository fileUploadRepository) {
        this.projectFolderService = projectFolderService;
        this.fileController = fileController;
        this.fileService = fileService;
        this.fileUploadRepository = fileUploadRepository;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<ProjectFolderDto>> getProjectFolders() {

        return ResponseEntity.ok().body(projectFolderService.getProjectFolders());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectFolderDto> getProjectFolder(@PathVariable("id") Long id) {

        ProjectFolderDto projectFolder = projectFolderService.getProjectFolder(id);

        return ResponseEntity.ok().body(projectFolder);
    }

    @GetMapping(path = "/user/{username}")
    public ResponseEntity<List<ProjectFolderDto>> getAllProjectFoldersByUser(@PathVariable("username") User user) {
        List<ProjectFolderDto> userProjectFolders;
        userProjectFolders = projectFolderService.getAllProjectFoldersByUser(user);
        return ResponseEntity.ok().body(userProjectFolders);
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Object> createProjectFolder(@Valid @RequestBody ProjectFolderInputDto folderInputDto) {

        ProjectFolderDto newFolder = projectFolderService.createProjectFolder(folderInputDto);

        return ResponseEntity.created(null).body(newFolder);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateProjectFolder(@PathVariable Long id, @RequestBody ProjectFolderDto dto) {

        projectFolderService.updateProjectFolder(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProjectFolder(@PathVariable Long id) {

        projectFolderService.deleteProjectFolder(id);

        return ResponseEntity.noContent().build();
    }

    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    @PostMapping(path = "/new/{username}")
    public ResponseEntity<ProjectFolderDto> createFolderForUser(
            @PathVariable("username") String username,
            @RequestBody ProjectFolderInputDto newFolderInput
    ) {
        ProjectFolderDto createdFolder = projectFolderService.createFolderForUser(username, newFolderInput);
        return ResponseEntity.created(null).body(createdFolder);
    }
}
