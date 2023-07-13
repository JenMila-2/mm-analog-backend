package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.FilmDevelopmentLogDto;
import com.example.mmanalog.dtos.InputDtos.FilmDevelopmentLogInputDto;
import com.example.mmanalog.models.User;
import com.example.mmanalog.services.FilmDevelopmentLogService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/filmdevelopmentlogs")
public class FilmDevelopmentLogController {

    private final FilmDevelopmentLogService filmDevelopmentLogService;

    public FilmDevelopmentLogController(FilmDevelopmentLogService filmDevelopmentLogService) {
        this.filmDevelopmentLogService = filmDevelopmentLogService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<FilmDevelopmentLogDto>> getFilmDevelopmentLogs() {

        return ResponseEntity.ok().body(filmDevelopmentLogService.getFilmDevelopmentLogs());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmDevelopmentLogDto> getFilmDevelopmentLog(@PathVariable("id") Long id) {

        FilmDevelopmentLogDto filmDevelopmentLogDto = filmDevelopmentLogService.getFilmDevelopmentLog(id);

        return ResponseEntity.ok().body(filmDevelopmentLogDto);
    }

    @GetMapping(path = "/user/{username}")
    public ResponseEntity<List<FilmDevelopmentLogDto>> getAllFilmDevelopmentLOgsByUser(@PathVariable("username") User user) {
        List<FilmDevelopmentLogDto> userFilmDevelopmentLogs;
        userFilmDevelopmentLogs = filmDevelopmentLogService.getAllFilmDevelopmentLogsByUser(user);
        return ResponseEntity.ok().body(userFilmDevelopmentLogs);
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Object> createFilmDevelopmentLog(@Valid @RequestBody FilmDevelopmentLogInputDto filmDevelopmentLogInputDto) {

        FilmDevelopmentLogDto filmDevelopmentLogDto = filmDevelopmentLogService.createFilmDevelopmentLog(filmDevelopmentLogInputDto);

        return ResponseEntity.created(null).body(filmDevelopmentLogDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateFilmDevelopmentLog(@PathVariable Long id, @Valid @RequestBody FilmDevelopmentLogInputDto updatedFilmDevelopmentLog) {

        FilmDevelopmentLogDto filmDevelopmentLogDto = filmDevelopmentLogService.updateFilmDevelopmentLog(id, updatedFilmDevelopmentLog);

        return ResponseEntity.ok().body(filmDevelopmentLogDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteFilmDevelopmentLog(@PathVariable Long id) {

        filmDevelopmentLogService.deleteFilmDevelopmentLog(id);

        return ResponseEntity.noContent().build();
    }


    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    @PostMapping(path = "/new/{username}")
    public ResponseEntity<FilmDevelopmentLogDto> createFilmDevelopmentLogForUser(
            @PathVariable("username") String username,
            @RequestBody FilmDevelopmentLogInputDto filmDevelopmentLogInputDto
    ) {
        FilmDevelopmentLogDto createdFilmDevelopmentLog = filmDevelopmentLogService.createFilmDevelopmentLogForUser(username, filmDevelopmentLogInputDto);
        return ResponseEntity.created(null).body(createdFilmDevelopmentLog);
    }

}
