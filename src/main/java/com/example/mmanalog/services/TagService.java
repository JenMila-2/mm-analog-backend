package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.TagDto;
import com.example.mmanalog.dtos.InputDtos.TagInputDto;
import com.example.mmanalog.models.Tag;
import com.example.mmanalog.repositories.TagRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagDto> getAllTags() {
        List<Tag> tagList = tagRepository.findAll();
        List<TagDto> tagDtoList = new ArrayList<>();

        for (Tag tag : tagList) {
            tagDtoList.add(transferToTagDto(tag));
        }
        return tagDtoList;
    }

    public TagDto getTagById(Long id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent()) {
            Tag tag = tagOptional.get();
            return transferToTagDto(tag);
        } else {
            throw new RecordNotFoundException("No photo found with id: " + id);
        }
    }

    public TagDto addNewTag(TagInputDto tagInputDto) {
        Tag tag = transferToTag(tagInputDto);
        tagRepository.save(tag);

        return transferToTagDto(tag);
    }

    public void deleteTag(@RequestBody Long id) {
        tagRepository.deleteById(id);
    }

    public TagDto updateTags(Long id, TagInputDto tagInputDto) {

        if (tagRepository.findById(id).isPresent()) {

            Tag tag = tagRepository.findById(id).get();

            Tag tag1 = transferToTag(tagInputDto);
            tag1.setId(tag.getId());

            tagRepository.save(tag1);

            return transferToTagDto(tag1);
        } else {
            throw new RecordNotFoundException("No tag found with id: " + id);
        }
    }

    public Tag transferToTag(TagInputDto tagInputDto) {
        Tag tag = new Tag();

        tag.setId(tagInputDto.getId());
        tag.setTagName(tagInputDto.getTagName());

        return tag;
    }

    public TagDto transferToTagDto(Tag tag) {
        TagDto tagDto = new TagDto();

        tagDto.setId(tag.getId());
        tagDto.setTagName(tag.getTagName());

        return tagDto;
    }
}
