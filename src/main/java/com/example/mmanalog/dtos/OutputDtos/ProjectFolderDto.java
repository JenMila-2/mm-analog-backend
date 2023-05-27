package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.Photo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFolderDto {

    public Long id;
    public String projectTitle;
    public String projectNote;
    public List<Photo> photos;
}
