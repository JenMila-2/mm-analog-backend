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
@Table(name = "photologs")
public class PhotoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String camera;
    private String stock;
    private int iso;
    private String aperture;
    private String shutterSpeed;
    private String exposureCompensation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTaken;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonIgnore
    private ProjectFolder projectFolder;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    @OneToOne
    FileUploadResponse file;
}
