package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.User;
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
public class PhotoGalleryInputDto {

    public Long id;
    @NotNull
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String photographerName;
    @Size(max=500, message="Bio cannot contain more than 500 characters.")
    public String shortBio;
    @NotNull
    public boolean publish;
    List<Photo> photoList;

    private Photo photo;
    private User user;
}
