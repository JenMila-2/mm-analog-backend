package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoTitle;
    private String camera;
    private String filmStock;
    private String filmFormat;
    private String developedBy;
    private int iso;
    private String aperture;
    private String shutterSpeed;
    private String exposureCompensation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_folder_id")
    @JsonIgnore
    private ProjectFolder projectFolder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_photo_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_gallery_id")
    @JsonIgnore
    private PhotoGallery photoGallery;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "photo_tag",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();
    //I used HashSet to avoid duplicate tags being added to a photo

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;
}
