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

    @PutMapping(path = "/{folderId}/images/{imageId}")
    public ResponseEntity<ProjectFolderDto> assignImageToFolder(
            @PathVariable("folderId") Long folderId,
            @PathVariable("imageId") Long imageId) {
        ProjectFolderDto projectFolderDto = projectFolderService.assignImageToFolder(folderId, imageId);
        return ResponseEntity.ok().body(projectFolderDto);
    }

    @GetMapping(path = "/{folderId}/images/{imageId}")
    public ResponseEntity<Resource> getFolderImage(@PathVariable("folderId") Long folderId, @PathVariable("imageId") Long imageId) {
        byte[] imageData = projectFolderService.getFolderImage(folderId, imageId);

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
            throw new RecordNotFoundException("Image with id: " + imageId + " or project folder with id: " + folderId + " do not exist or not found");
        }
    }

    /* Method below only returns the image data and not the actual images */
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
