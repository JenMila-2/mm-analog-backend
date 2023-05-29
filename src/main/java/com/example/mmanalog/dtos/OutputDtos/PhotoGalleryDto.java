package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.Photo;
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
public class PhotoGalleryDto {

    public Long id;
    public String photographerName;
    public String shortBio;
    public boolean publish;

    @JsonIgnore
    private Photo photo;
    @JsonIgnore
    List<Photo> photoList;
}
