package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.User;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private User user;
    @JsonIgnore
    private Photo photo;
    @JsonIgnore
    public List<Photo> photos;
}
