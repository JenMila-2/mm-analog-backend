package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.PhotoDto;
import com.example.mmanalog.dtos.InputDtos.PhotoInputDto;
import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.services.PhotoService;
import com.example.mmanalog.services.ProjectFolderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final ProjectFolderService projectFolderService;

    public PhotoController(PhotoService photoService, ProjectFolderService projectFolderService) {
        this.photoService = photoService;
        this.projectFolderService = projectFolderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> getPhotoById(@PathVariable Long id) {
        PhotoDto photoDto = photoService.getPhotoById(id);
        return ResponseEntity.ok().body(photoDto);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<PhotoDto>> getAllPhotos() {

        return ResponseEntity.ok().body(photoService.getAllPhotos());
    }

    @GetMapping(path = "/filmstock/{filmStock}")
    public ResponseEntity<List<PhotoDto>> getPhotoByFilmStock(@PathVariable String filmStock) {

        return ResponseEntity.ok(photoService.getPhotoByFilmStock(filmStock));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhoto(@PathVariable Long id) {

        photoService.deletePhoto(id);

        return ResponseEntity.noContent().build();
    }
    /////////
    @PostMapping(path = "")
    public ResponseEntity<Object> addPhotoMetadata(@Valid @RequestBody PhotoInputDto photoInputDto) {

        PhotoDto dtoPhoto = photoService.addPhotoMetadata(photoInputDto);

        return ResponseEntity.created(null).body(dtoPhoto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePhotoMetadata(@PathVariable Long id, @Valid @RequestBody PhotoInputDto newPhoto) {

        PhotoDto dtoPhoto = photoService.updatePhotoMetadata(id, newPhoto);

        return ResponseEntity.ok().body(dtoPhoto);
    }

    //// *** Methods related to the relationship between entities *** ////
    @PutMapping(path = "/{id}/folder/{folderId}")
    public ResponseEntity<Object> assignPhotoToFolder(@PathVariable("id") Long id, @PathVariable("folderId") Long folderId) {
        PhotoDto photoDto = photoService.assignPhotoToFolder(id, folderId);

        return ResponseEntity.ok().body(photoDto);
    }

    @PutMapping(path = "/{id}/user/{userId}")
    public ResponseEntity<Object> assignPhotoToUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        PhotoDto photoDto = photoService.assignPhotoToUser(id, userId);

        return ResponseEntity.ok().body(photoDto);
    }

    //Alle relatie controllers aanpassen naar onderstaand voorbeeld, is korter?
    @PutMapping(path = "/{id}/tag/{tagId}")
    public ResponseEntity<String> assignTagToPhoto(@PathVariable Long id, @PathVariable Long tagId) {
        return ResponseEntity.ok(photoService.assignTagToPhoto(id, tagId));
    }
}


