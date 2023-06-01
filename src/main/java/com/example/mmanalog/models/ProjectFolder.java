package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
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

    @OneToMany(mappedBy = "projectFolder")
    @JsonIgnore
    private List<Photo> photos;

    @ManyToOne
    @JoinColumn(name = "user_folder_id")
    @JsonIgnore
    private User user;
}
