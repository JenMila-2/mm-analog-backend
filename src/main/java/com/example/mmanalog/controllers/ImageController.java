package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.ProjectFolderDto;
import com.example.mmanalog.dtos.UserDto;
import com.example.mmanalog.models.Image;
import com.example.mmanalog.services.ProjectFolderService;
import com.example.mmanalog.services.UserService;
import com.example.mmanalog.repositories.ImageRepository;
import com.example.mmanalog.utilities.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /* Method for users to upload an image in project folder */

    @PostMapping(path = "/upload/folder/image")
    public ResponseEntity<ImageUploadResponse> uploadImageUser(@RequestParam("username") String username, @RequestParam("projectFolder") Long projectFolderId, @RequestParam("image") MultipartFile file) throws IOException {

        UserDto user = userService.getUser(username);
        ProjectFolderDto projectFolder = projectFolderService.getProjectFolder(projectFolderId);

        // Specify the folder path within the project folder where you want to save the image
        String folderPath = projectFolder.getProjectTitle().replaceAll("\\s+", "_") + "/";

        // Specify the file path within the project folder
        String filePath = folderPath + file.getOriginalFilename();

        // Save the image to the specified file path within the project folder
        File savedFile = new File(filePath);
        file.transferTo(savedFile);

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename() + " for user: " + user.getUsername() + " in project folder: " + projectFolder.getProjectTitle()));
    }

    @GetMapping(path = {"/image/{projectFolderId}/{imageName}"})
    public ResponseEntity<Resource> getFolderImage(@PathVariable("projectFolderId") Long projectFolderId, @PathVariable("imageName") String imageName) throws IOException {
        ProjectFolderDto projectFolder = projectFolderService.getProjectFolder(projectFolderId);
        String folderPath = projectFolder.getProjectTitle().replaceAll("\\s+", "_") + "/";
        String filePath = folderPath + imageName;

        // Load the image file
        Path imageFile = Paths.get(filePath);
        Resource resource = new UrlResource(imageFile.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
