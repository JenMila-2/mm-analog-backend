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

}
