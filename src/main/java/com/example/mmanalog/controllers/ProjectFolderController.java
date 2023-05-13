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
@RequestMapping("/projectfolders")
public class ProjectFolderController {

private final ProjectFolderService projectFolderService;

public ProjectFolderController(ProjectFolderService projectFolderService) {
    this.projectFolderService = projectFolderService''
}


}
