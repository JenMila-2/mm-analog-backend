package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.Photo;
import com.example.mmanalog.models.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFolderInputDto {

    public Long id;
    @NotNull
    @Size(max = 75, message = "Title must be between 0 and 75 characters.")
    public String projectTitle;
    public String projectNote;

    public List<Photo> photos;
    public Photo photo;
    public User user;
}