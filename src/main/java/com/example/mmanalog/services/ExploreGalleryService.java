package com.example.mmanalog.services;

import com.example.mmanalog.dtos.ExploreGalleryDto;
import com.example.mmanalog.dtos.ExploreGalleryInputDto;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.models.ExploreGallery;
import com.example.mmanalog.repositories.ExploreGalleryRepository;
import com.example.mmanalog.exceptions.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExploreGalleryService {

private final ExploreGalleryRepository exploreGalleryRepository;

public ExploreGalleryService(ExploreGalleryRepository exploreGalleryRepository) {
    this.exploreGalleryRepository = exploreGalleryRepository;
}

public List<ExploreGalleryDto> getAllPhotographerGalleries() {
    List<ExploreGallery> galleries = exploreGalleryRepository.findAll();
    List<ExploreGalleryDto> galleryDtos = new ArrayList<>();

    for (ExploreGallery gallery : galleries) {
        galleryDtos.add(transferExploreGalleryToDto(gallery));
    }
    return galleryDtos;
}

public List<ExploreGalleryDto> getAllPhotos() {
    List<ExploreGallery> explorePhotos = exploreGalleryRepository.findAll();
    List<ExploreGalleryDto> explorePhotoDtos = new ArrayList<>();

    for (ExploreGallery photo : explorePhotos) {
        explorePhotoDtos.add(transferExploreGalleryToDto(photo));
    }
    return explorePhotoDtos;
}

public ExploreGallery transferToExploreGallery(ExploreGalleryInputDto exploreGalleryInputDto) {

    var exploreGallery = new ExploreGallery();

    exploreGallery.setPhotographerName(exploreGalleryInputDto.getPhotographerName());
    exploreGallery.setPhotoTitle(exploreGalleryInputDto.getPhotoTitle());
    exploreGallery.setImageURL(exploreGalleryInputDto.getImageURL());

    return exploreGallery;
}

public ExploreGalleryDto transferExploreGalleryToDto(ExploreGallery exploreGallery) {
    ExploreGalleryDto exploreGalleryDto = new ExploreGalleryDto();

    exploreGalleryDto.setId(exploreGallery.getId());
    exploreGalleryDto.setPhotographerName(exploreGallery.getPhotographerName());
    exploreGalleryDto.setPhotoTitle(exploreGallery.getPhotoTitle());
    exploreGalleryDto.setImageURL(exploreGallery.getImageURL());

    return exploreGalleryDto;
}

}
