package com.example.mmanalog.controllers;

import com.example.mmanalog.dtos.OutputDtos.TagDto;
import com.example.mmanalog.dtos.InputDtos.TagInputDto;
import com.example.mmanalog.services.TagService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<TagDto>> getAllTags() {

        return ResponseEntity.ok().body(tagService.getAllTags());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TagDto> getOneTag(@PathVariable("id") Long id) {

        TagDto tag = tagService.getTagById(id);

        return ResponseEntity.ok().body(tag);
    }

    @PostMapping(path = "")
    public ResponseEntity<Object> addNewTag(@Valid @RequestBody TagInputDto tagInputDto) {

        TagDto tagDto = tagService.addNewTag(tagInputDto);

        return ResponseEntity.created(null).body(tagDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id) {

        tagService.deleteTag(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateTags(@PathVariable Long id, @Valid @RequestBody TagInputDto updatedTag) {

        TagDto tagDto = tagService.updateTags(id, updatedTag);

        return ResponseEntity.ok().body(tagDto);
    }
}
