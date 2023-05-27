package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ExploreGalleryDto;
import com.example.mmanalog.services.ExploreGalleryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "/exploregalleries")
public class ExploreGalleryController {

    private final ExploreGalleryService exploreGalleryService;

    public ExploreGalleryController(ExploreGalleryService exploreGalleryService) {
        this.exploreGalleryService = exploreGalleryService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<ExploreGalleryDto>> getAllPhotoGalleries() {

        return ResponseEntity.ok().body(exploreGalleryService.getAllPhotographerGalleries());
    }

    @GetMapping(path = "/photos")
    public ResponseEntity<List<ExploreGalleryDto>> getAllPhotosInGalleries() {

        return ResponseEntity.ok().body(exploreGalleryService.getAllPhotos());
    }
}

