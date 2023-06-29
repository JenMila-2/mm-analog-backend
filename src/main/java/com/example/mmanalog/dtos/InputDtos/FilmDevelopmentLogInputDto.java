package com.example.mmanalog.dtos.InputDtos;

import com.example.mmanalog.models.User;
import jakarta.validation.constraints.*;
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
public class FilmDevelopmentLogInputDto {

    public Long id;
    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String nameFilm;

    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String project;
    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String camera;
    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String filmStock;
    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String filmFormat;
    public int shotAtIso;
    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String developmentProcess;
    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate rollStarted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate rollFinished;
    public boolean exposed;
    public boolean developed;
    public boolean scanned;
    @Size(max=75, message="This field cannot contain more than 75 characters.")
    public String developedByLab;

    public User user;
}
