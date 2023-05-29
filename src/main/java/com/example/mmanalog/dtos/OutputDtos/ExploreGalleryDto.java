package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.PhotoGallery;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public String photographerName;
    public String photoTitle;
    public String imageURL;

    @JsonIgnore
    List<PhotoGallery> photoGalleryList;
    @JsonIgnore
    private PhotoGallery photoGallery;
}
