package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.PhotoGallery;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExploreGalleryDto {

    public Long id;
    public String photographerName;
    public String photoTitle;
    public String imageURL;

    @JsonIgnore
    List<PhotoGallery> photoGalleryList;
    @JsonIgnore
    private PhotoGallery photoGallery;
}
