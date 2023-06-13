package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {

    public Long id;
    public String photoTitle;
    public String camera;
    public String filmStock;
    public String filmFormat;
    public String developedBy;
    public int iso;
    public String aperture;
    public String shutterSpeed;
    public String exposureCompensation;

    public ProjectFolder projectFolder;
    public PhotoGallery photoGallery;
    public User user;
    public Tag tag;
}


