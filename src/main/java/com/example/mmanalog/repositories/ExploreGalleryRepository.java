package com.example.mmanalog.repositories;

import com.example.mmanalog.models.ExploreGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExploreGalleryRepository extends JpaRepository<ExploreGallery, Long> {

    @Query("SELECT eg FROM ExploreGallery eg")
    ExploreGallery getExploreGallery();
}
