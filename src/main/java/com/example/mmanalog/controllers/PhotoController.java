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

}
