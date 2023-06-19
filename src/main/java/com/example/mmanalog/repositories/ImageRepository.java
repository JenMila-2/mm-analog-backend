package com.example.mmanalog.repositories;

import com.example.mmanalog.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByName(String name);

    @Override
    Optional<Image> findById(Long id);
}
