package com.example.mmanalog.dtos.OutputDtos;

import com.example.mmanalog.models.User;
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
public class FilmDevelopmentLogDto {

    public Long id;
    public String rollName;
    public String project;
    public String camera;
    public String stock;
    public String format;
    public int shotAtIso;
    public String developmentProcess;
    public String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   public LocalDate rollStarted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate rollFinished;
    public boolean exposed;
    public boolean developed;
    public boolean scanned;
    public String developedByLab;

    public User user;
}
