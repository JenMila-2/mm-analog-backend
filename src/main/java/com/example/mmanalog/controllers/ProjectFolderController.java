package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.ProjectFolderDto;
import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.services.ProjectFolderService;
import com.example.mmanalog.services.UserService;
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
    public ResponseEntity<Iterable<ProjectFolderDto>> getAllFolders() {

    return ResponseEntity.ok().body(projectFolderService.getFolders());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectFolderDto> getProjectFolder(@PathVariable("id") Long id) {

    ProjectFolderDto projectFolder = projectFolderService.getFolderById(id);

    return ResponseEntity.ok().body(projectFolder);
    }

    @PostMapping(path = "")
    public ResponseEntity<ProjectFolderDto> addProjectFolder(@Valid @RequestBody ProjectFolderDto folderDto) {

    ProjectFolderDto dtoFolder = projectFolderService.addProjectFolder((folderDto));

    return ResponseEntity.created(null).body(dtoFolder);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteProjectFolder(@PathVariable Long id) {

    projectFolderService.deleteProjectFolder(id);

    return ResponseEntity.noContent().build();
    }
}
