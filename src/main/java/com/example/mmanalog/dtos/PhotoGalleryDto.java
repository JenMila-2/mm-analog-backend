package com.example.mmanalog.dtos;

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

    public Long id;
    @NotNull
    @Size(max=50, message="Gallery name cannot contain more than 50 characters.")
    public String galleryName;
    @Size(max=500, message="Bio cannot contain more than 500 characters.")
    public String shortBio;
    public String tags;
    @NotNull
    public boolean isPublic;
}
