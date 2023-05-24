package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<ProjectFolder> userProjectFolders;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Photo> userPhotos;
}
