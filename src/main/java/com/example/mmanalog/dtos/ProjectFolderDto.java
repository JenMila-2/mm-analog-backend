package com.example.mmanalog.dtos;

import com.example.mmanalog.models.Photo;
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
public class ProjectFolderDto {

    public Long id;
    @NotNull(message = "Title is required!")
    public String projectTitle;
    public String projectNote;
    public List<Photo> photos;
}
