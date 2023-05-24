package com.example.mmanalog.dtos;
import com.example.mmanalog.models.Photo;

import java.util.List;
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
    public boolean isPublic;
    List<Photo> photoList;
}
