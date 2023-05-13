package com.example.mmanalog.dtos;

import jakarta.validation.constraints.NotNull;
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

    public Long folderId;
    @NotNull(message = "Title is required!")
    public String projectTitle;
    public String projectNotes;

    // public List<Photo> photos;

}
