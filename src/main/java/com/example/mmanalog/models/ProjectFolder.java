package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projectfolders")
public class ProjectFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectTitle;
    private String projectConcept;

    @OneToOne
    FileUploadResponse file;

    @OneToMany(mappedBy = "projectFolder")
    @JsonIgnore
    private List<PhotoLog> photoLogs;

    @ManyToOne
    @JoinColumn(name = "username")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "projectFolder")
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
