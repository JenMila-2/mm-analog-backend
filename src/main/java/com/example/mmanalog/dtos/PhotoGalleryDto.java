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
    @Size(max=75)
    public String galleryTitle;
    @Size(max=500)
    public String shortBio;
    public String tags;
    @NotNull
    public boolean isPublic;
}
