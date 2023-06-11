package com.example.mmanalog.controllers;

import com.example.mmanalog.models.PhotoUploadResponse;
import com.example.mmanalog.services.PhotoUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin
public class PhotoUploadController {

    private final PhotoUploadService photoUploadService;

    public PhotoUploadController(PhotoUploadService photoUploadService) {
        this.photoUploadService = photoUploadService;
    }

    @PostMapping(path = "/upload")
    PhotoUploadResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        String imageUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                .path(Objects.requireNonNull(file.getOriginalFilename()))
                .toUriString();
        String contentType = file.getContentType();
        String fileName = photoUploadService.storeFile(file, imageUrl);
        return new PhotoUploadResponse(fileName, contentType, imageUrl);
    }

    @GetMapping(path = "/download/{fileName}")
    ResponseEntity<Resource> downloadPhoto(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = photoUploadService.downloadFile(fileName);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok().contentType(MediaType
                .parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName" + resource.getFilename())
                .body(resource);
    }
}
