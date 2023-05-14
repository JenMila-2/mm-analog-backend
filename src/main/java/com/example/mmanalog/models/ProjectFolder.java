package com.example.mmanalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projectfolders")
public class ProjectFolder {

    @Id
    @GeneratedValue
    private Long id;
    private String projectTitle;
    private String projectNote;

    //private List<Photo> photos;
}