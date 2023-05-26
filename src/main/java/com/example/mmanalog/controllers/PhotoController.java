package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.PhotoDto;
import com.example.mmanalog.dtos.PhotoInputDto;
import com.example.mmanalog.services.PhotoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "/photos")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<PhotoDto>> getAllPhotos() {

        return ResponseEntity.ok().body(photoService.getAllPhotos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PhotoDto> getPhoto(@PathVariable("id") Long id) {

        PhotoDto photo = photoService.getPhotoById(id);

        return ResponseEntity.ok().body(photo);
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> addPhoto(@Valid @RequestBody PhotoInputDto photoInputDto) {

        PhotoDto dtoPhoto = photoService.addPhoto(photoInputDto);

        return ResponseEntity.created(null).body(dtoPhoto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhoto(@PathVariable Long id) {

        photoService.deletePhoto(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePhotoMetadata(@PathVariable Long id, @Valid @RequestBody PhotoInputDto newPhoto) {

        PhotoDto dtoPhoto = photoService.updatePhotoMetadata(id, newPhoto);

        return ResponseEntity.ok().body(dtoPhoto);
    }

    //Method to assign photos to a project folder
    @PutMapping(path = "/{photoId}/folder/{folderId}")
    public ResponseEntity<Object> assignPhotoToFolder(@PathVariable("photoId") Long photoId, @PathVariable("folderId") Long folderId) {
        PhotoDto dtoPhoto = photoService.assignPhotoToFolder(photoId, folderId);

        return ResponseEntity.ok().body(dtoPhoto);
    }

    //Method to assign photos to a photo gallery
    @PutMapping(path = "/{photoId}/gallery/{galleryId}")
    public ResponseEntity<Object> assignPhotoToPhotoGallery(@PathVariable("photoId") Long photoId, @PathVariable("galleryId") Long galleryId) {
        PhotoDto photoDto = photoService.assignPhotoToGallery(photoId, galleryId);

        return ResponseEntity.ok().body(photoDto);
    }

    @PutMapping(path = "/{photoId}/user/{userId}")
    public ResponseEntity<Object> assignPhotoToUser(@PathVariable("photoId") Long photoId, @PathVariable("userId") Long userId) {
        PhotoDto photoDto = photoService.assignPhotoToUser(photoId, userId);

        return ResponseEntity.ok().body(photoDto);
    }
}

