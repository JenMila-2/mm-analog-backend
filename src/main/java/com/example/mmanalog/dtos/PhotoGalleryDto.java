package com.example.mmanalog.dtos;
import com.example.mmanalog.models.Photo;

import jakarta.validation.constraints.*;
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

    public Long photoId;
    @NotNull
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String photographerName;
    @Size(max=500, message="Bio cannot contain more than 500 characters.")
    public String shortBio;
    public String tag;
    @NotNull
    public boolean isPublic;
    List<Photo> photoList;
}
