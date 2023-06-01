package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.PhotoGallery;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExploreGalleryDto {

    public Long id;

    List<PhotoGallery> photoGalleries;
    public PhotoGallery photoGallery;
}
