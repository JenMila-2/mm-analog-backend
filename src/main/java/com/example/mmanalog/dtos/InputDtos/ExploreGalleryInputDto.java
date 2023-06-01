package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.PhotoGallery;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExploreGalleryInputDto {

    public Long id;

    List<PhotoGallery> photoGalleries;
    public PhotoGallery photoGallery;
}