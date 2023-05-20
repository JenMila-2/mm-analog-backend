package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.ProjectFolderDto;
import com.example.mmanalog.dtos.ProjectFolderInputDto;
import com.example.mmanalog.services.ProjectFolderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/projectfolders")
public class ProjectFolderController {

    private final ProjectFolderService projectFolderService;

    public ProjectFolderController(ProjectFolderService projectFolderService) {
        this.projectFolderService = projectFolderService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<ProjectFolderDto>> getAllProjectFolders() {

        return ResponseEntity.ok().body(projectFolderService.getProjectFolders());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectFolderDto> getProjectFolder(@PathVariable("id") Long id) {

        ProjectFolderDto projectFolder = projectFolderService.getProjectFolderById(id);

        return ResponseEntity.ok().body(projectFolder);
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> addProjectFolder(@Valid @RequestBody ProjectFolderInputDto folderInputDto) {

        ProjectFolderDto dtoFolder = projectFolderService.addProjectFolder(folderInputDto);

        return ResponseEntity.created(null).body(dtoFolder);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProjectFolder(@PathVariable Long id) {

        projectFolderService.deleteProjectFolder(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateProjectFolder(@PathVariable Long id, @Valid @RequestBody ProjectFolderInputDto newProjectFolder) {

        ProjectFolderDto dtoProjectFolder = projectFolderService.updateProjectFolder(id, newProjectFolder);

        return ResponseEntity.ok().body(dtoProjectFolder);
    }
}
