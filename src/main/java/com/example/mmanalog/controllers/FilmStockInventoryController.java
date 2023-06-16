package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.FilmStockInventoryDto;
import com.example.mmanalog.dtos.InputDtos.FilmStockInventoryInputDto;
import com.example.mmanalog.services.FilmStockInventoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "/filmstockinventories")
public class FilmStockInventoryController {

    private final FilmStockInventoryService filmStockInventoryService;

    public FilmStockInventoryController(FilmStockInventoryService filmStockInventoryService) {
        this.filmStockInventoryService = filmStockInventoryService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<FilmStockInventoryDto>> getAllFilmStockInventories() {

        return ResponseEntity.ok().body(filmStockInventoryService.getAllFilmStockInventories());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilmStockInventoryDto> getFilmStockInventory(@PathVariable("id") Long id) {

        FilmStockInventoryDto filmStockInventoryDto = filmStockInventoryService.getFilmStockInventoryById(id);

        return ResponseEntity.ok().body(filmStockInventoryDto);
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> createFilmStockInventory(@Valid @RequestBody FilmStockInventoryInputDto filmStockInventoryInputDto) {

        FilmStockInventoryDto filmStockInventoryDto = filmStockInventoryService.createFilmStockInventory(filmStockInventoryInputDto);

        return ResponseEntity.created(null).body(filmStockInventoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteFilmStockInventory(@PathVariable Long id) {

        filmStockInventoryService.deleteFilmStockInventory(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateFilmStockInventory(@PathVariable Long id, @Valid @RequestBody FilmStockInventoryInputDto newFilmStockInventory) {

        FilmStockInventoryDto dtoFilmStockInventory = filmStockInventoryService.updateFilmStockInventory(id, newFilmStockInventory);

        return ResponseEntity.ok().body(dtoFilmStockInventory);
    }

    //// **** Methods related to the relationship between entities **** ////
    @PutMapping(path = "/{id}/user/{userId}")
    public ResponseEntity<Object> assignFilmStockInventoryToUser(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        FilmStockInventoryDto filmStockInventoryDto = filmStockInventoryService.assignFilmStockInventoryToUser(id, userId);

        return ResponseEntity.ok().body(filmStockInventoryDto);
    }
}
