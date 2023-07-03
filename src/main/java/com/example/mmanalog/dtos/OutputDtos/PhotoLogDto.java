package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoLogDto {

    public Long id;
    public String photoTitle;
    public String camera;
    public String filmStock;
    public String filmFormat;
    public int shotAtIso;
    public String aperture;
    public String shutterSpeed;
    public String exposureCompensation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate dateTaken;
    public String developedByLab;
    public String notes;

    public ProjectFolder projectFolder;

    public User user;
}


