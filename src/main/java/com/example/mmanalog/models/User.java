package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ProjectFolder> userProjectFolders;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Photo> userPhotos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_photo_gallery_id")
    PhotoGallery photoGallery;
}
