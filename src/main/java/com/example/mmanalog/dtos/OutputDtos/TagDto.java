package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.Photo;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {


    public Long id;
    public String tagName;

    public Photo photo;
}
