package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoInputDto {

    public Long id;
    @NotNull
    @Size(max = 75, message = "Title must be between 0 and 75 characters.")
    public String photoTitle;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String camera;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String filmStock;
    public String filmFormat;
    @Size(max=50, message="This field cannot contain more than 50 characters.")
    public String developedBy;
    public int iso;
    public String aperture;
    public String shutterSpeed;
    public String exposureCompensation;

    public User user;
    public PhotoGallery photoGallery;
    public ProjectFolder projectFolder;
    public Tag tag;
}

