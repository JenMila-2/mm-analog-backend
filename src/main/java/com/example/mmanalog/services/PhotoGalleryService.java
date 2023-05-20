package com.example.mmanalog.services;

import com.example.mmanalog.dtos.PhotoGalleryDto;
import com.example.mmanalog.dtos.PhotoGalleryInputDto;
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
        List<PhotoGallery> photoGalleries = photoGalleryRepository.findAll();
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

    public PhotoGalleryDto addPhotoGallery(PhotoGalleryInputDto inputDtoPhotoGallery) {

        PhotoGallery photoGallery = transferToPhotoGallery(inputDtoPhotoGallery);
        photoGalleryRepository.save(photoGallery);

        return transferPhotoGalleryToDto(photoGallery);
    }

    public void deletePhotoGallery(@RequestBody Long id) {
        photoGalleryRepository.deleteById(id);
    }

    public PhotoGalleryDto updatePhotoGallery(Long id, PhotoGalleryInputDto photoGalleryInputDto) {

        if (photoGalleryRepository.findById(id).isPresent()) {

            PhotoGallery photoGallery = photoGalleryRepository.findById(id).get();

            PhotoGallery photoGallery1 = transferToPhotoGallery(photoGalleryInputDto);
            photoGallery1.setId(photoGallery.getId());

            return transferPhotoGalleryToDto(photoGallery1);
        } else {
            throw new RecordNotFoundException("No photo gallery found with id: " + id);
        }
    }

    public PhotoGallery transferToPhotoGallery(PhotoGalleryInputDto photoGalleryInputDto) {
        var photoGallery = new PhotoGallery();

        photoGallery.setPhotographerName(photoGalleryInputDto.getPhotographerName());
        photoGallery.setShortBio(photoGalleryInputDto.getShortBio());
        photoGallery.setTag(photoGalleryInputDto.getTag());
        photoGallery.setPublic(photoGalleryInputDto.isPublic());

        return photoGallery;
    }

    public PhotoGalleryDto transferPhotoGalleryToDto(PhotoGallery photoGallery) {
        PhotoGalleryDto photoGalleryDto = new PhotoGalleryDto();

        photoGalleryDto.setId(photoGallery.getId());
        photoGalleryDto.setPhotographerName(photoGallery.getPhotographerName());
        photoGalleryDto.setShortBio(photoGallery.getShortBio());
        photoGalleryDto.setTag(photoGallery.getTag());
        photoGalleryDto.setPublic(photoGallery.isPublic());

        return photoGalleryDto;
    }
}
