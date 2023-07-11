package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.FilmStockInventoryDto;
import com.example.mmanalog.dtos.InputDtos.FilmStockInventoryInputDto;
import com.example.mmanalog.services.FilmStockInventoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/filmstockinventories")
public class FilmStockInventoryController {

    private final FilmStockInventoryService filmStockInventoryService;

    public FilmStockInventoryController(FilmStockInventoryService filmStockInventoryService) {
        this.filmStockInventoryService = filmStockInventoryService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<FilmStockInventoryDto>> getFilmStockInventories() {

        return ResponseEntity.ok().body(filmStockInventoryService.getFilmStockInventories());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmStockInventoryDto> getFilmStockInventory(@PathVariable("id") Long id) {

        FilmStockInventoryDto filmStockInventoryDto = filmStockInventoryService.getFilmStockInventory(id);

        return ResponseEntity.ok().body(filmStockInventoryDto);
    }

    @PostMapping(path = "/new")
    public ResponseEntity<Object> createFilmStockInventory(@Valid @RequestBody FilmStockInventoryInputDto filmStockInventoryInputDto) {

        FilmStockInventoryDto filmStockInventoryDto = filmStockInventoryService.createFilmStockInventory(filmStockInventoryInputDto);

        return ResponseEntity.created(null).body(filmStockInventoryDto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateFilmStockInventory(@PathVariable Long id, @Valid @RequestBody FilmStockInventoryInputDto updatedFilmStockInventory) {

        FilmStockInventoryDto dtoFilmStockInventory = filmStockInventoryService.updateFilmStockInventory(id, updatedFilmStockInventory);

        return ResponseEntity.ok().body(dtoFilmStockInventory);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteFilmStockInventory(@PathVariable Long id) {

        filmStockInventoryService.deleteFilmStockInventory(id);

        return ResponseEntity.noContent().build();
    }


    //*-----------------------------Methods related to the relationship between entities-----------------------------*//

    @PostMapping(path = "/new/{username}")
    public ResponseEntity<FilmStockInventoryDto> createFilmStockInventoryForUser(
            @PathVariable("username") String username,
            @RequestBody FilmStockInventoryInputDto filmStockInventoryInputDto
    ) {
        FilmStockInventoryDto createdFilmStockInventory = filmStockInventoryService.createFilmStockInventoryForUser(username, filmStockInventoryInputDto);
        return ResponseEntity.created(null).body(createdFilmStockInventory);
    }
}
