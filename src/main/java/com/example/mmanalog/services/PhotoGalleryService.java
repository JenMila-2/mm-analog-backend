package com.example.mmanalog.services;

import com.example.mmanalog.dtos.PhotoGalleryDto;
import com.example.mmanalog.models.PhotoGallery;
import com.example.mmanalog.repositories.PhotoGalleryRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoGalleryService {

    private final PhotoGalleryRepository photoGalleryRepository;

    public PhotoGalleryService(PhotoGalleryRepository photoGalleryRepository) {
        this.photoGalleryRepository = photoGalleryRepository;
    }

    public List<PhotoGalleryDto> getAllPhotoGalleries() {
        Iterable<PhotoGallery> photoGalleries = photoGalleryRepository.findAll();
        List<PhotoGalleryDto> photoGalleryDtos = new ArrayList<>();

        for (PhotoGallery photoGallery : photoGalleries) {
            photoGalleryDtos.add(transferPhotoGalleryToDto(photoGallery));
        }
        return photoGalleryDtos;
    }

    public PhotoGalleryDto getPhotoGalleryById(Long id) {
        Optional<PhotoGallery> photoGalleryOptional = photoGalleryRepository.findById(id);
        if (photoGalleryOptional.isPresent()) {
            PhotoGallery photoGallery = photoGalleryOptional.get();
            return transferPhotoGalleryToDto((photoGallery));
        } else {
            throw new RecordNotFoundException("No photo gallery found with id: " + id);
        }
    }

    public PhotoGallery transferToPhotoGallery(PhotoGalleryDto photoGalleryDto) {

        var photoGallery = new PhotoGallery();

        photoGallery.setId(photoGalleryDto.getId());
        photoGallery.setGalleryTitle(photoGalleryDto.getGalleryTitle());
        photoGallery.setShortBio(photoGalleryDto.getShortBio());
        photoGallery.setPublic(photoGalleryDto.isPublic());

        return photoGallery;
    }

    public PhotoGalleryDto transferPhotoGalleryToDto(PhotoGallery photoGallery) {
        PhotoGalleryDto photoGalleryDto = new PhotoGalleryDto();

        photoGalleryDto.id = photoGallery.getId();
        photoGalleryDto.galleryTitle = photoGallery.getGalleryTitle();
        photoGalleryDto.shortBio = photoGallery.getShortBio();
        photoGalleryDto.isPublic = photoGallery.isPublic();

        return photoGalleryDto;
    }
}
