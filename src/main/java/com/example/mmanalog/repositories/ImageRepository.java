package com.example.mmanalog.repositories;

import com.example.mmanalog.models.Image;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByName(String name);

    @Override
    Optional<Image> findById(Long id);
}
