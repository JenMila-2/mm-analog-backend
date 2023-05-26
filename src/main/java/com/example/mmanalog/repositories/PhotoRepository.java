package com.example.mmanalog.repositories;

import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.ProjectFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByProjectFolder(ProjectFolder projectFolder);

    List<Photo> findPhotoByFilmStock(String filmStock);
}

