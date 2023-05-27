package com.example.mmanalog.dtos.OutputDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public String fStop;
    public String shutterSpeed;
    public String exposureCompensation;

    /*private ProjectFolderDto projectFolderDto;
    private PhotoGalleryDto photoGalleryDto;
    private UserDto userDto;*/
}

