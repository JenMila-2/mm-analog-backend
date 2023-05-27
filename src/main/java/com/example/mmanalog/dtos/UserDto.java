package com.example.mmanalog.dtos;

import com.example.mmanalog.models.PhotoGallery;
import com.example.mmanalog.models.ProjectFolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    public List<Photo> photos;

    @JsonIgnore
    public List<PhotoGallery> photoGalleries;
    @JsonIgnore
    public List<ProjectFolder> projectFolders;
    @JsonIgnore
    public List<String> authorities;
}

// public Set<Authority> getAuthorities() { return authorities }
// public void setAuthorities(Set<Authority> authorities) { this.authorities = authorities; }