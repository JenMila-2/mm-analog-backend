package com.example.mmanalog.dtos.OutputDtos;
import com.example.mmanalog.models.Photo;

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
