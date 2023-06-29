package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filmdevelopmentlogs")
public class FilmDevelopmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameFilm;
    private String project;
    private String camera;
    private String filmStock;
    private String filmFormat;
    private int shotAtIso;
    private String developmentProcess;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rollStarted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rollFinished;
    private boolean exposed;
    private boolean developed;
    private boolean scanned;
    private String developedByLab;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;
}
