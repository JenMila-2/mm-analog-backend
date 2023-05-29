package com.example.mmanalog.dtos;

import com.example.mmanalog.models.PhotoGallery;
import com.example.mmanalog.models.ProjectFolder;
import com.example.mmanalog.models.Photo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public Long id;
    @NotNull
    public String name;
    @NotBlank
    @Email
    public String email;
    @NotBlank
    @Size(min=6, max=30)
    public String password;
    @NotNull
    public boolean enabled;

    @JsonIgnore
    public Set<String> authorities;

    @JsonIgnore
    public List<Photo> photos;
    @JsonIgnore
    public List<PhotoGallery> photoGalleries;
    @JsonIgnore
    public List<ProjectFolder> projectFolders;

    private ProjectFolder projectFolder;
    private Photo photo;
    private PhotoGallery photoGallery;
}