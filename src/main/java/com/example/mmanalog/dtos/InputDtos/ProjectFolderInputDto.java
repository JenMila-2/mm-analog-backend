package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.Image;
import com.example.mmanalog.models.PhotoLog;
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
    @NotNull(message = "Project title cannot be empty")
    @Size(min = 5, max = 75, message = "Title must be between 5 and 75 characters")
    public String projectTitle;
    @Size(min = 5, max = 250, message = "Project concept must be between 5 and 250 characters")
    public String projectConcept;
    public List<PhotoLog> photoLogs;
    public PhotoLog photoLog;

    public User user;
    public Image file;
}