package com.example.mmanalog.controllers;

import com.example.mmanalog.services.FileService;
import com.example.mmanalog.models.FileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin
public class FileController {
    private final FileService fileService;


    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = "/upload")
    FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) {

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        String fileName = fileService.uploadFile(file, url);

        return new FileUploadResponse(fileName, contentType, url);
    }

    @GetMapping(path = "/download/{fileName}")
    ResponseEntity<Resource> downloadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileService.downloadFile(fileName);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    @PostMapping(path = "/upload/{username}")
    FileUploadResponse uploadFileForUser(@RequestParam("file") MultipartFile file, @PathVariable String username) {
        String contentType = file.getContentType();
        String fileName = fileService.uploadFileForUser(file, username);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(username) // Include the username in the download URL
                .path("/")
                .path(fileName)
                .toUriString();

        return new FileUploadResponse(fileName, contentType, url);
    }

    @GetMapping(path = "/download/{username}/{fileName}")
    ResponseEntity<Resource> downloadSingleFileForUser(@PathVariable String username, @PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.downloadFileForUser(username, fileName);

        String mimeType;
        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @DeleteMapping(path = "/delete/{username}/{fileName}")
    ResponseEntity<String> deleteFileForUser(@PathVariable String username, @PathVariable String fileName) {
        fileService.deleteFileForUser(username, fileName);
        return ResponseEntity.ok("File deleted successfully.");
    }

    @PostMapping(path = "/upload/project/{folderId}")
    FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Long folderId) {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/project/")
                .path(folderId.toString())
                .path("/")
                .path(Objects.requireNonNull(file.getOriginalFilename()))
                .toUriString();


        String contentType = file.getContentType();

        String fileName = fileService.assignFileToFolder(file, folderId);

        return new FileUploadResponse(fileName, contentType, url);
    }

    @GetMapping(path = "/download/project/{folderId}/{fileName}")
    ResponseEntity<Resource> downloadFileFromFolder(@PathVariable Long folderId, @PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.downloadFileFromFolder(folderId, fileName);

        String mimeType;
        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

    @DeleteMapping(path = "/delete/project/{folderId}/{fileName}")
    ResponseEntity<String> deleteFileFromFolder(@PathVariable Long folderId, @PathVariable String fileName) {
        fileService.deleteFileFromFolder(folderId, fileName);
        return ResponseEntity.ok("File deleted successfully.");
    }
}