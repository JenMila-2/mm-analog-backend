package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
public class User {

    @Id
    @Email
    @GeneratedValue
    @Column(nullable = false, unique = true)

    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProjectFolder> userProjectFolders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Photo> userPhotos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_gallery_id")
    @JsonIgnore
    PhotoGallery photoGallery;
}
