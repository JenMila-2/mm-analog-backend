package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.PhotoLogDto;
import com.example.mmanalog.dtos.InputDtos.PhotoLogInputDto;
import com.example.mmanalog.services.PhotoLogService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "/photologs")
public class PhotoLogController {

    private final PhotoLogService photoLogService;

    public PhotoLogController(PhotoLogService photoLogService) {
        this.photoLogService = photoLogService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<PhotoLogDto>> getPhotoLogs() {

        return ResponseEntity.ok().body(photoLogService.getPhotoLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoLogDto> getPhotoLog(@PathVariable("id") Long id) {

        PhotoLogDto photoLog = photoLogService.getPhotoLog(id);

        return ResponseEntity.ok().body(photoLog);
    }

    @GetMapping(path = "/films_stock/{film_stock}")
    public ResponseEntity<List<PhotoLogDto>> getByPhotoLogFilmStock(@PathVariable String filmStock) {

        return ResponseEntity.ok(photoLogService.getByPhotoLogFilmStock(filmStock));
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Object> createPhotoLog(@Valid @RequestBody PhotoLogInputDto photoLogInputDto) {

        PhotoLogDto photoLog = photoLogService.createPhotoLog(photoLogInputDto);

        return ResponseEntity.created(null).body(photoLog);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePhotoLog(@PathVariable Long id, @Valid @RequestBody PhotoLogInputDto updatedPhotoLog) {

        PhotoLogDto photoLog = photoLogService.updatePhotoLog(id, updatedPhotoLog);

        return ResponseEntity.ok().body(photoLog);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhotoLog(@PathVariable Long id) {

        photoLogService.deletePhotoLog(id);

        return ResponseEntity.noContent().build();
    }

    /* Methods related to the relationship between entities */

    @PutMapping(path = "/{id}/folder/{folderId}")
    public ResponseEntity<Object> assignPhotoLogToFolder(@PathVariable("id") Long id, @PathVariable("folderId") Long folderId) {
        PhotoLogDto photoLogDto = photoLogService.assignPhotoLogToFolder(id, folderId);

        return ResponseEntity.ok().body(photoLogDto);
    }

    @PutMapping(path = "/{id}/user/{username}")
    public ResponseEntity<Object> assignPhotoLogToUser(@PathVariable("id") Long id, @PathVariable("username") String username) {
        PhotoLogDto photoLogDto = photoLogService.assignPhotoLogToUser(id, username);

        return ResponseEntity.ok().body(photoLogDto);
    }

    @PostMapping(path = "/new/{username}")
    public ResponseEntity<PhotoLogDto> createPhotoLogForUser(
            @PathVariable("username") String username,
            @RequestBody PhotoLogInputDto newPhotoLogInput
    ) {
        PhotoLogDto createdPhotoLog = photoLogService.createPhotoLogForUser(username, newPhotoLogInput);
        return ResponseEntity.created(null).body(createdPhotoLog);
    }

    @PostMapping(path = "/user/{username}/folder/{folderId}")
    public ResponseEntity<PhotoLogDto> createPhotoLogForProjectFolderForUser(
            @PathVariable("username") String username,
            @PathVariable("folderId") Long folderId,
            @RequestBody PhotoLogInputDto newPhotoLogInput
    ) {
        PhotoLogDto createdPhotoLogForFolderUser = photoLogService.createPhotoLogForProjectFolderForUser(username, folderId, newPhotoLogInput);
        return ResponseEntity.created(null).body(createdPhotoLogForFolderUser);
    }
}


