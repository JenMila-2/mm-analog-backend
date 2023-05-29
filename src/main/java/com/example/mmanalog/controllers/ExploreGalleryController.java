package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ExploreGalleryDto;
import com.example.mmanalog.models.ExploreGallery;
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

    @GetMapping("/explore")
    public ResponseEntity<ExploreGallery> getExploreGallery() {
        ExploreGallery exploreGallery = exploreGalleryService.getExploreGallery();
        if (exploreGallery != null) {
            return ResponseEntity.ok(exploreGallery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

