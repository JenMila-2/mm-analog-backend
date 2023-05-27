package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.PhotoGallery;
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
public class ExploreGalleryInputDto {

    public Long id;
    @NotNull
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String photographerName;
    @NotNull
    @Size(max = 75, message = "Title must be between 0 and 75 characters!")
    public String photoTitle;
    @NotNull
    public String imageURL;

    List<PhotoGallery> photoGalleryList;
    private PhotoGallery photoGallery;
}