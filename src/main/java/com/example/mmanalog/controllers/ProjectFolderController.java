package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.exceptions.BadRequestException;
import com.example.mmanalog.services.ProjectFolderService;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.List;

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

    // *** Methods related to the relationship between entities ***
    @PutMapping(path = "/{id}/user/{userId}")
    public ResponseEntity<Object> assignFolderToUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        ProjectFolderDto projectFolderDto = projectFolderService.assignFolderToUser(id, userId);

        return ResponseEntity.ok().body(projectFolderDto);
    }

    @PutMapping(path = "/{folderId}/images/{imageId}")
    public ResponseEntity<ProjectFolderDto> assignImageToFolder(
            @PathVariable("folderId") Long folderId,
            @PathVariable("imageId") Long imageId) {
        ProjectFolderDto projectFolderDto = projectFolderService.assignImageToFolder(folderId, imageId);
        return ResponseEntity.ok().body(projectFolderDto);
    }

    @GetMapping(path = "/{folderId}/images/{imageId}")
    public ResponseEntity<Resource> getFolderImages(@PathVariable("folderId") Long folderId, @PathVariable("imageId") Long imageId) {
        byte[] imageData = projectFolderService.getFolderImages(folderId, imageId);

        if (imageData != null && imageData.length > 0) {
            String imageName = "example.jpg";
            String imageType = "image/jpeg";

            byte[] processedImageData = ImageUtility.decompressImage(imageData);

            ByteArrayResource resource = new ByteArrayResource(processedImageData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(imageType));
            headers.setContentLength(resource.contentLength());
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(imageName).build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            throw new BadRequestException("Error. Folder or user not found.");
        }
    }
}
