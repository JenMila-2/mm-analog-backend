package com.example.mmanalog.dtos.OutputDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoGalleryDto {

    public Long id;
    public String photographerName;
    public String shortBio;
    public boolean publish;
}
