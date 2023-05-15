package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.PhotoDto;
import com.example.mmanalog.services.PhotoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/photos")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Iterable<PhotoDto>> getAllPhotos() {
        return ResponseEntity.ok().body(photoService.getAllPhotos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PhotoDto> getPhoto(@PathVariable("id") Long id) {

        PhotoDto photo = photoService.getPhotoById(id);

        return ResponseEntity.ok().body(photo);
    }

    @PostMapping(path = "")
    public ResponseEntity<PhotoDto> addPhoto(@Valid @RequestBody PhotoDto photoDto) {

        PhotoDto dtoPhoto = photoService.addPhoto((photoDto));

        return ResponseEntity.created(null).body(dtoPhoto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhoto(@PathVariable Long id) {

        photoService.deletePhoto(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PhotoDto> updatePhotoMetadata(@PathVariable Long id, @Valid @RequestBody PhotoDto newPhoto) {

        PhotoDto dtoPhoto = photoService.updatePhotoMetadata(id, newPhoto);

        return ResponseEntity.ok().body(dtoPhoto);
    }
}
