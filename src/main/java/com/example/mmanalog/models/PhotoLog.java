package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "photologs")
public class PhotoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoTitle;
    private String camera;
    private String filmStock;
    private String filmFormat;
    private int shotAtIso;
    private String aperture;
    private String shutterSpeed;
    private String exposureCompensation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rollStarted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rollFinished;
    private String developedByLab;
    private boolean scanned;
    private String notes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    @JsonIgnore
    private ProjectFolder projectFolder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
