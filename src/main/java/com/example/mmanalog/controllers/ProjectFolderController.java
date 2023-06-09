package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.InputDtos.ProjectFolderInputDto;
import com.example.mmanalog.services.ProjectFolderService;
import com.example.mmanalog.exceptions.BadRequestException;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
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
    public ResponseEntity<Object> updateProjectFolder(@PathVariable Long id, @Valid @RequestBody ProjectFolderInputDto updatedProjectFolder) {

        ProjectFolderDto dtoProjectFolder = projectFolderService.updateProjectFolder(id, updatedProjectFolder);

        return ResponseEntity.ok().body(dtoProjectFolder);
    }

    //// **** Methods related to the relationship between entities **** ////
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
            throw new BadRequestException("Image not found with id: " + imageId);
        }
    }

    @DeleteMapping(path = "/{folderId}/images/{imageId}")
    public ResponseEntity<String> deleteFolderImage(@PathVariable("folderId") Long folderId, @PathVariable("imageId") Long imageId) {
        try {
            projectFolderService.deleteFolderImage(folderId, imageId);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException("Image with id: " + imageId + " or folder with id: " + folderId + " do not exist or not found");
        }
    }

    //// **** Specials **** ////
    //Method below only returns the image data and not the actual images//
    @GetMapping(path = "/{folderId}/images")
    public ResponseEntity<List<byte[]>> getAllFolderImages(@PathVariable("folderId") Long folderId) {
        try {
            List<byte[]> images = projectFolderService.getAllFolderImages(folderId);

            if (!images.isEmpty()) {
                return new ResponseEntity<>(images, HttpStatus.OK);
            } else {
                throw new RecordNotFoundException("No images found for folder with id: " + folderId);
            }
        } catch (Exception e) {
            throw new BadRequestException("Error while retrieving data.");
        }
    }
}
