package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFolderDto {

    public Long id;
    public String projectTitle;
    public String projectNote;

    @JsonIgnore
    private User user;
    @JsonIgnore
    private Photo photo;
    @JsonIgnore
    public List<Photo> photos;
}
