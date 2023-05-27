package com.example.mmanalog.repositories;

import com.example.mmanalog.models.PhotoGallery;
import com.example.mmanalog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoGalleryRepository extends JpaRepository<PhotoGallery, Long> {

    List<PhotoGallery> findPhotoGalleryByUser(User user);
}
