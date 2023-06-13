package com.example.mmanalog.services;

import com.example.mmanalog.dtos.OutputDtos.PhotoDto;
import com.example.mmanalog.dtos.InputDtos.PhotoInputDto;
import com.example.mmanalog.models.*;
import com.example.mmanalog.repositories.*;
import com.example.mmanalog.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final ProjectFolderRepository projectFolderRepository;
    private final PhotoGalleryRepository photoGalleryRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    public PhotoService(PhotoRepository photoRepository, ProjectFolderRepository projectFolderRepository, PhotoGalleryRepository photoGalleryRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.photoRepository = photoRepository;
        this.projectFolderRepository = projectFolderRepository;
        this.photoGalleryRepository = photoGalleryRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    /////////////////////

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

    public List<PhotoDto> getPhotoByFilmStock(String filmStock) {
        List<Photo> filmStockPhotoList = photoRepository.findByFilmStock(filmStock);
        List<PhotoDto> filmStockPhotoListDto = new ArrayList<>();

        for (Photo photo : filmStockPhotoList) {
            filmStockPhotoListDto.add(transferToPhotoDto(photo));
        }
        return filmStockPhotoListDto;
    }

    public PhotoDto addPhotoMetadata(PhotoInputDto inputDtoPhoto) {
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
        Photo photo = new Photo();

        photo.setId(photoInputDto.getId());
        photo.setPhotoTitle(photoInputDto.getPhotoTitle());
        photo.setCamera(photoInputDto.getCamera());
        photo.setFilmStock(photoInputDto.getFilmStock());
        photo.setFilmFormat(photoInputDto.getFilmFormat());
        photo.setDevelopedBy(photoInputDto.getDevelopedBy());
        photo.setIso(photoInputDto.getIso());
        photo.setAperture(photoInputDto.getAperture());
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
        photoDto.setAperture(photo.getAperture());
        photoDto.setShutterSpeed(photo.getShutterSpeed());
        photoDto.setExposureCompensation(photo.getExposureCompensation());
        photoDto.setProjectFolder(photo.getProjectFolder());
        photoDto.setPhotoGallery(photo.getPhotoGallery());
        photoDto.setUser(photo.getUser());
        //photoDto.setPhotoUploadResponse(photo.getPhotoUploadResponse());

        return photoDto;
    }

    // *** Methods related to the relationship between entities ***
    public PhotoDto assignPhotoToFolder(Long id, Long folderId) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        Optional<ProjectFolder> folderOptional = projectFolderRepository.findById(folderId);

        if (photoOptional.isPresent() && folderOptional.isPresent()) {
            Photo photo = photoOptional.get();
            ProjectFolder folder = folderOptional.get();

            photo.setProjectFolder(folder);
            photoRepository.save(photo);

            return transferToPhotoDto(photo);
        } else {
            throw new RecordNotFoundException("Photo or project folder not found.");
        }
    }

    public PhotoDto assignPhotoToGallery(Long id, Long galleryId) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        Optional<PhotoGallery> galleryOptional = photoGalleryRepository.findById(galleryId);

        if (photoOptional.isPresent() && galleryOptional.isPresent()) {
            Photo photo = photoOptional.get();
            PhotoGallery photoGallery = galleryOptional.get();

            photo.setPhotoGallery(photoGallery);
            photoRepository.save(photo);

            return transferToPhotoDto(photo);
        } else {
            throw new RecordNotFoundException("Photo or photo gallery not found.");
        }
    }

    public PhotoDto assignPhotoToUser(Long id, Long userId) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalPhoto.isPresent() && optionalUser.isPresent()) {
            Photo photo = optionalPhoto.get();
            User user = optionalUser.get();

            photo.setUser(user);
            photoRepository.save(photo);

            return transferToPhotoDto(photo);
        } else {
            throw new RecordNotFoundException("Photo or user not found.");
        }
    }

    public String assignTagToPhoto(Long id, Long tagId) throws RecordNotFoundException {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        if (optionalPhoto.isEmpty() && optionalTag.isEmpty()) {
            throw new RecordNotFoundException("No tag or photo found.");
        }
        Photo photo = optionalPhoto.get();
        Tag tag = optionalTag.get();
        Set<Tag> tagList = photo.getTags();
        tagList.add(tag);
        photo.setTags(tagList);
        photoRepository.save(photo);
        return "Tag added to photo";
    }
}



