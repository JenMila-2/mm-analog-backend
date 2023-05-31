package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.ExploreGalleryDto;
import com.example.mmanalog.dtos.InputDtos.ExploreGalleryInputDto;
import com.example.mmanalog.models.ExploreGallery;
import com.example.mmanalog.repositories.ExploreGalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ExploreGalleryService {

private final ExploreGalleryRepository exploreGalleryRepository;

public ExploreGalleryService(ExploreGalleryRepository exploreGalleryRepository) {
    this.exploreGalleryRepository = exploreGalleryRepository;
}

    public ExploreGallery getExploreGallery() {
        return exploreGalleryRepository.getExploreGallery();
    }

public ExploreGallery transferToExploreGallery(ExploreGalleryInputDto exploreGalleryInputDto) {

    var exploreGallery = new ExploreGallery();

    exploreGallery.setId(exploreGalleryInputDto.getId());

    return exploreGallery;
}

public ExploreGalleryDto transferExploreGalleryToDto(ExploreGallery exploreGallery) {
    ExploreGalleryDto exploreGalleryDto = new ExploreGalleryDto();

    exploreGalleryDto.setId(exploreGallery.getId());

    return exploreGalleryDto;
}
}
