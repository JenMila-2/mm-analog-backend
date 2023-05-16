package com.example.mmanalog.services;

import com.example.mmanalog.dtos.ExploreGalleryDto;
import com.example.mmanalog.models.ExploreGallery;
import com.example.mmanalog.repositories.ExploreGalleryRepository;
import com.example.mmanalog.exceptions.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExploreGalleryService {

private final ExploreGalleryRepository exploreGalleryRepository;

public ExploreGalleryService(ExploreGalleryRepository exploreGalleryRepository) {
    this.exploreGalleryRepository = exploreGalleryRepository;
}

}
