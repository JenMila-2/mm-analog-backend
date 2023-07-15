package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.PhotoLogDto;
import com.example.mmanalog.dtos.InputDtos.PhotoLogInputDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.services.PhotoLogService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<PhotoLogDto> getPhotoLog(@PathVariable("id") Long id) {

        PhotoLogDto photoLog = photoLogService.getPhotoLog(id);

        return ResponseEntity.ok().body(photoLog);
    }

    @GetMapping(path = "/user/{username}")
    public ResponseEntity<List<PhotoLogDto>> getAllPhotoLogsByUser(@PathVariable("username") User user) {
        List<PhotoLogDto> userPhotoLogs;
        userPhotoLogs = photoLogService.getAllPhotoLogsByUser(user);
        return ResponseEntity.ok().body(userPhotoLogs);
    }

    @GetMapping(path = "/film_stock/{film_stock}")
    public ResponseEntity<List<PhotoLogDto>> getByPhotoLogFilmStock(@PathVariable String film_stock) {

        return ResponseEntity.ok(photoLogService.getByPhotoLogFilmStock(film_stock));
    }

    @GetMapping(path = "/film_format/{film_format}")
    public ResponseEntity<List<PhotoLogDto>> getByPhotoLogFilmFormat(@PathVariable String film_format) {

        return ResponseEntity.ok(photoLogService.getByPhotoLogFilmFormat(film_format));
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Object> createPhotoLog(@Valid @RequestBody PhotoLogInputDto photoLogInputDto) {

        PhotoLogDto photoLog = photoLogService.createPhotoLog(photoLogInputDto);

        return ResponseEntity.created(null).body(photoLog);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updatePhotoLog(@PathVariable("id") Long id, @RequestBody PhotoLogDto dto) {

        photoLogService.updatePhotoLog(id, dto);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletePhotoLog(@PathVariable Long id) {

        photoLogService.deletePhotoLog(id);

        return ResponseEntity.noContent().build();
    }


    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    @PostMapping(path = "/new/{username}")
    public ResponseEntity<PhotoLogDto> createPhotoLogForUser(
            @PathVariable("username") String username,
            @RequestBody PhotoLogInputDto newPhotoLogInput
    ) {
        PhotoLogDto createdPhotoLog = photoLogService.createPhotoLogForUser(username, newPhotoLogInput);
        return ResponseEntity.created(null).body(createdPhotoLog);
    }

    @PostMapping(path = "/new/{username}/folder/{folderId}")
    public ResponseEntity<PhotoLogDto> createPhotoLogForProjectFolderForUser(
            @PathVariable("username") String username,
            @PathVariable("folderId") Long folderId,
            @RequestBody PhotoLogInputDto newPhotoLogInput
    ) {
        PhotoLogDto createdPhotoLogForFolderUser = photoLogService.createPhotoLogForProjectFolderForUser(username, folderId, newPhotoLogInput);
        return ResponseEntity.created(null).body(createdPhotoLogForFolderUser);
    }
}


