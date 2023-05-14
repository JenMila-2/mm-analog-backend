package com.example.mmanalog.services;

import com.example.mmanalog.dtos.PhotoDto;
import com.example.mmanalog.models.Photo;
import com.example.mmanalog.repositories.PhotoRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<PhotoDto> getAllPhotos() {
        Iterable<Photo> photoList = photoRepository.findAll();
        List<PhotoDto> photoListDto = new ArrayList<>();

        for (Photo photo : photoList) {
            photoListDto.add(transferToPhotoDto(photo));
        }
        return photoListDto;
    }

    public PhotoDto getPhotoById(Long id) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        if (photoOptional.isPresent()) {
            Photo photo = photoOptional.get();
            return transferToPhotoDto(photo);
        } else {
            throw new RecordNotFoundException("No photo found with id: " + id);
        }
    }

    public Photo transferToPhoto(PhotoDto photoDto) {

        var photo = new Photo();

        photo.setId(photoDto.getId());
        photo.setPhotoTitle(photoDto.getPhotoTitle());
        photo.setCamera(photoDto.getCamera());
        photo.setFilmStock(photoDto.getFilmStock());
        photo.setFilmFormat(photoDto.getFilmFormat());
        photo.setDevelopedBy(photoDto.getDevelopedBy());
        photo.setIso(photoDto.getIso());
        photo.setFStop(photoDto.getFStop());
        photo.setShutterSpeed(photoDto.getShutterSpeed());
        photo.setExposureCompensation(photoDto.getExposureCompensation());

        return photo;
    }

    public PhotoDto transferToPhotoDto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();

        photoDto.id = photo.getId();
        photoDto.photoTitle = photo.getPhotoTitle();
        photoDto.camera = photo.getCamera();
        photoDto.filmStock = photo.getFilmStock();
        photoDto.filmFormat = photo.getFilmFormat();
        photoDto.developedBy = photo.getDevelopedBy();
        photoDto.iso = photo.getIso();
        photoDto.fStop = photo.getFStop();
        photoDto.shutterSpeed = photo.getShutterSpeed();
        photoDto.exposureCompensation = photo.getExposureCompensation();

        return photoDto;
    }

}
