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
    @Size(min = 5, max = 75, message = "This field must be between 5 and 75 characters long")
    public String rollName;
    @Size(min = 5, max = 75, message = "This field must be between 5 and 75 characters long")
    public String project;
    @Size(min = 5, max = 75, message = "This field must be between 5 and 75 characters long")
    public String camera;
    @Size(min = 5, max = 75, message = "This field must be between 5 and 75 characters long")
    public String filmStock;
    public String filmFormat;
    public int shotAtIso;
    @Size(min = 5, max = 75, message = "This field must be between 5 and 75 characters long")
    public String developmentProcess;
    public String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate rollStarted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate rollFinished;
    public boolean exposed;
    public boolean developed;
    public boolean scanned;
    @Size(min = 5, max = 75, message = "This field must be between 5 and 75 characters long")
    public String developedByLab;

    public User user;
}
