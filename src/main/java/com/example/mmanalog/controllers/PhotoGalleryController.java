package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.PhotoGalleryDto;
import com.example.mmanalog.dtos.InputDtos.PhotoGalleryInputDto;
import com.example.mmanalog.services.PhotoGalleryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "/photogalleries")
public class PhotoGalleryController {

    private final PhotoGalleryService photoGalleryService;

    public PhotoGalleryController(PhotoGalleryService photoGalleryService) {
        this.photoGalleryService = photoGalleryService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<PhotoGalleryDto>> getAllPhotoGalleries() {

        return ResponseEntity.ok().body(photoGalleryService.getAllPhotoGalleries());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PhotoGalleryDto> getPhotoGallery(@PathVariable("id") Long id) {

        PhotoGalleryDto photoGallery = photoGalleryService.getPhotoGalleryById(id);

        return ResponseEntity.ok().body(photoGallery);
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> addPhotoGallery(@Valid @RequestBody PhotoGalleryInputDto photoGalleryInputDto) {

        PhotoGalleryDto dtoPhotoGallery = photoGalleryService.addPhotoGallery(photoGalleryInputDto);

        return ResponseEntity.created(null).body(dtoPhotoGallery);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhotoGallery(@PathVariable Long id) {

        photoGalleryService.deletePhotoGallery(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePhotoGallery(@PathVariable Long id, @Valid PhotoGalleryInputDto newPhotoGallery) {

        PhotoGalleryDto dtoPhotoGallery = photoGalleryService.updatePhotoGallery(id, newPhotoGallery);

        return ResponseEntity.ok().body(dtoPhotoGallery);
    }
}
