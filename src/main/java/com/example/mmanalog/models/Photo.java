package com.example.mmanalog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue
    private Long id;
    private String photoTitle;
    private String camera;
    private String filmStock;
    private String filmFormat;
    private String developedBy;
    private int iso;
    private String fStop;
    private String shutterSpeed;
    private String exposureCompensation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_folder_id")
    private ProjectFolder projectFolder;

    @ManyToOne
    @JoinColumn(name = "user_photo_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "photo_gallery_id")
    private PhotoGallery photoGallery;
}
