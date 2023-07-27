package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.services.ProjectFolderService;
import com.example.mmanalog.exceptions.BadRequestException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/projectfolders")
public class ProjectFolderController {

    private final ProjectFolderService projectFolderService;

    public ProjectFolderController(ProjectFolderService projectFolderService) {
        this.projectFolderService = projectFolderService;
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

    @PostMapping(path = "/{folderId}/images")
    public ResponseEntity<ProjectFolderDto> uploadImageToFolder(
            @PathVariable("folderId") Long folderId,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        ProjectFolderDto projectFolderDto = projectFolderService.uploadImageToFolder(folderId, file);
        return ResponseEntity.ok().body(projectFolderDto);
    }

    @GetMapping(path = "/{folderId}/images/{imageName}")
    public ResponseEntity<Resource> getFolderImageByName(
            @PathVariable("folderId") Long folderId,
            @PathVariable("imageName") String imageName
    ) {
        byte[] imageData = projectFolderService.getFolderImageByName(folderId, imageName);

        if (imageData != null && imageData.length > 0) {
            String imageType = "image/jpeg";

            ByteArrayResource resource = new ByteArrayResource(imageData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(imageType));
            headers.setContentLength(resource.contentLength());
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(imageName).build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            throw new BadRequestException("No image found with name: " + imageName + " in folder with id: " + folderId);
        }
    }

    @DeleteMapping(path = "/{folderId}/images/{imageName}")
    public ResponseEntity<String> deleteFolderImageByName(
            @PathVariable("folderId") Long folderId,
            @PathVariable("imageName") String imageName
    ) {
        try {
            projectFolderService.deleteFolderImageByName(folderId, imageName);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException("Image with name: " + imageName + " or project folder with id: " + folderId + " does not exist or not found");
        }
    }
}
