package com.example.mmanalog.services;

import com.example.mmanalog.dtos.PhotoDto;
import com.example.mmanalog.dtos.PhotoInputDto;
import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.repositories.PhotoGalleryRepository;
import com.example.mmanalog.repositories.PhotoRepository;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import com.example.mmanalog.repositories.ProjectFolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final ProjectFolderRepository projectFolderRepository;

    public PhotoService(PhotoRepository photoRepository, ProjectFolderRepository projectFolderRepository) {
        this.photoRepository = photoRepository;
        this.projectFolderRepository = projectFolderRepository;
    }

    public List<PhotoDto> getAllPhotos() {
        List<Photo> photoList = photoRepository.findAll();
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

    public PhotoDto addPhoto(PhotoInputDto inputDtoPhoto) {
        Photo photo = transferToPhoto(inputDtoPhoto);
        photoRepository.save(photo);

        return transferToPhotoDto(photo);
    }

    public void deletePhoto(@RequestBody Long id) {
        photoRepository.deleteById(id);
    }

    public PhotoDto updatePhotoMetadata(Long id, PhotoInputDto inputDtoPhoto) {

        if (photoRepository.findById(id).isPresent()) {

            Photo photo = photoRepository.findById(id).get();

            Photo photo1 = transferToPhoto(inputDtoPhoto);
            photo1.setId(photo.getId());

            photoRepository.save(photo1);

            return transferToPhotoDto(photo1);
        } else {
            throw new RecordNotFoundException("No photo found with id: " + id);
        }
    }

    public Photo transferToPhoto(PhotoInputDto photoInputDto) {
        var photo = new Photo();

        photo.setPhotoTitle(photoInputDto.getPhotoTitle());
        photo.setCamera(photoInputDto.getCamera());
        photo.setFilmStock(photoInputDto.getFilmStock());
        photo.setFilmFormat(photoInputDto.getFilmFormat());
        photo.setDevelopedBy(photoInputDto.getDevelopedBy());
        photo.setIso(photoInputDto.getIso());
        photo.setFStop(photoInputDto.getFStop());
        photo.setShutterSpeed(photoInputDto.getShutterSpeed());
        photo.setExposureCompensation(photoInputDto.getExposureCompensation());

        return photo;
    }

    public PhotoDto transferToPhotoDto(Photo photo) {
        PhotoDto photoDto = new PhotoDto();

        photoDto.setId(photo.getId());
        photoDto.setPhotoTitle(photo.getPhotoTitle());
        photoDto.setCamera(photo.getCamera());
        photoDto.setFilmStock(photo.getFilmStock());
        photoDto.setFilmFormat(photo.getFilmFormat());
        photoDto.setDevelopedBy(photo.getDevelopedBy());
        photoDto.setIso(photo.getIso());
        photoDto.setFStop(photo.getFStop());
        photoDto.setShutterSpeed(photo.getShutterSpeed());
        photoDto.setExposureCompensation(photo.getExposureCompensation());

        return photoDto;
    }

    //Method to assign photos to a project folder.
    public PhotoDto assignPhotoToFolder(Long photoId, Long folderId) {
        Optional<Photo> photoOptional = photoRepository.findById(photoId);
        Optional<ProjectFolder> folderOptional = projectFolderRepository.findById(folderId);

        if (photoOptional.isPresent() && folderOptional.isPresent()) {
            Photo photo = photoOptional.get();
            ProjectFolder folder = folderOptional.get();

            photo.setProjectFolder(folder);
            photoRepository.save(photo);

            return transferToPhotoDto(photo);
        } else {
            throw new RecordNotFoundException("Photo or project folder not found");
        }
    }
}
