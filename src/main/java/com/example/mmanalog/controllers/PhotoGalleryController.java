package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.PhotoGalleryDto;
import com.example.mmanalog.services.PhotoGalleryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/photogalleries")
public class PhotoGalleryController {

    private final PhotoGalleryService photoGalleryService;

    public PhotoGalleryController(PhotoGalleryService photoGalleryService) {
        this.photoGalleryService = photoGalleryService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Iterable<PhotoGalleryDto>> getAllPhotoGalleries() {

        return ResponseEntity.ok().body(photoGalleryService.getAllPhotoGalleries());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PhotoGalleryDto> getPhotoGallery(@PathVariable("id") Long id) {

        PhotoGalleryDto photoGallery = photoGalleryService.getPhotoGalleryById(id);

        return ResponseEntity.ok().body(photoGallery);
    }

    @PostMapping(path = "")
    public ResponseEntity<PhotoGalleryDto> addPhotoGallery(@Valid @RequestBody PhotoGalleryDto photoGalleryDto) {

        PhotoGalleryDto dtoPhotoGallery = photoGalleryService.addPhotoGallery(photoGalleryDto);

        return ResponseEntity.created(null).body(dtoPhotoGallery);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhotoGallery(@PathVariable Long id) {

        photoGalleryService.deletePhotoGallery(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PhotoGalleryDto> updatePhotoGallery(@PathVariable Long id, @Valid PhotoGalleryDto newPhotoGallery) {

        PhotoGalleryDto dtoPhotoGallery = photoGalleryService.updatePhotoGallery(id, newPhotoGallery);

        return ResponseEntity.ok().body(dtoPhotoGallery);
    }

}
