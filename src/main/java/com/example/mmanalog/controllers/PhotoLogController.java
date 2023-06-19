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
    public ResponseEntity<PhotoLogDto> getPhotoLogById(@PathVariable("id") Long id) {

        PhotoLogDto photoLog = photoLogService.getPhotoLogById(id);

        return ResponseEntity.ok().body(photoLog);
    }

    @GetMapping(path = "/filmstock/{filmStock}")
    public ResponseEntity<List<PhotoLogDto>> getByFilmStock(@PathVariable String filmStock) {

        return ResponseEntity.ok(photoLogService.getByFilmStock(filmStock));
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> addPhotoLog(@Valid @RequestBody PhotoLogInputDto photoLogInputDto) {

        PhotoLogDto photoLog = photoLogService.addPhotoLog(photoLogInputDto);

        return ResponseEntity.created(null).body(photoLog);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhotoLog(@PathVariable Long id) {

        photoLogService.deletePhotoLog(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePhotoLog(@PathVariable Long id, @Valid @RequestBody PhotoLogInputDto updatedPhotoLog) {

        PhotoLogDto photoLog = photoLogService.updatePhotoLog(id, updatedPhotoLog);

        return ResponseEntity.ok().body(photoLog);
    }

    //// **** Methods related to the relationship between entities **** ////
    @PutMapping(path = "/{id}/folder/{folderId}")
    public ResponseEntity<Object> assignPhotoLogToFolder(@PathVariable("id") Long id, @PathVariable("folderId") Long folderId) {
        PhotoLogDto photoLogDto = photoLogService.assignPhotoLogToFolder(id, folderId);

        return ResponseEntity.ok().body(photoLogDto);
    }

    @PutMapping(path = "/{id}/user/{userId}")
    public ResponseEntity<Object> assignPhotoToUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        PhotoLogDto photoLogDto = photoLogService.assignPhotoLogToUser(id, userId);

        return ResponseEntity.ok().body(photoLogDto);
    }

    @PostMapping(path = "/new/{userId}")
    public ResponseEntity<PhotoLogDto> createPhotoLogForUser(
            @PathVariable("userId") Long userId,
            @RequestBody PhotoLogInputDto newPhotoLogInput
    ) {
        PhotoLogDto createdPhotoLog = photoLogService.createPhotoLogForUser(userId, newPhotoLogInput);
        return ResponseEntity.created(null).body(createdPhotoLog);
    }

    @PostMapping(path = "/new/user/{userId}/folder/{folderId}")
    public ResponseEntity<PhotoLogDto> createPhotoLogForProjectFolderForUser(
            @PathVariable("userId") Long userId,
            @PathVariable("folderId") Long folderId,
            @RequestBody PhotoLogInputDto newPhotoLogInput
    ) {
        PhotoLogDto createdPhotoLogForFolderUser = photoLogService.createPhotoLogForProjectFolderForUser(userId, folderId, newPhotoLogInput);
        return ResponseEntity.created(null).body(createdPhotoLogForFolderUser);
    }
}


