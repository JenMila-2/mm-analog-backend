package com.example.mmanalog.controllers;

import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.ImageUploadResponse;
import com.example.mmanalog.services.ProjectFolderService;
import com.example.mmanalog.services.UserService;
import com.example.mmanalog.repositories.ImageRepository;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin()
public class ImageController {
    @Autowired
    ImageRepository imageRepository;

    private final UserService userService;
    private final ProjectFolderService projectFolderService;

    public ImageController(UserService userService, ProjectFolderService projectFolderService) {
        this.userService = userService;
        this.projectFolderService = projectFolderService;
    }

    @GetMapping(path = {"/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .id(dbImage.get().getId())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = {"/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }

    @PostMapping(path = "/upload/image")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }
}