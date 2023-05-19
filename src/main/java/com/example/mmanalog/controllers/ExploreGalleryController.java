package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.ExploreGalleryDto;
import com.example.mmanalog.dtos.PhotoGalleryDto;
import com.example.mmanalog.services.ExploreGalleryService;
import com.example.mmanalog.services.PhotoGalleryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/exploregalleries")
public class ExploreGalleryController {

    private final ExploreGalleryService exploreGalleryService;

    public ExploreGalleryController(ExploreGalleryService exploreGalleryService) {
        this.exploreGalleryService = exploreGalleryService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Iterable<ExploreGalleryDto>> getAllPhotoGalleries() {

        return ResponseEntity.ok().body(exploreGalleryService.getAllPhotographerGalleries());
    }

    @GetMapping(path = "/photos")
    public ResponseEntity<Iterable<ExploreGalleryDto>> getAllPhotosInGalleries() {

        return ResponseEntity.ok().body(exploreGalleryService.getAllPhotos());
    }
}
