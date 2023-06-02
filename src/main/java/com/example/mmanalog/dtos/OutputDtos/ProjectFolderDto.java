package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFolderDto {

    public Long id;
    public String projectTitle;
    public String projectNote;

    public User user;
}
