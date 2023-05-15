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
@Table(name = "photogalleries")
public class PhotoGallery {

    @Id
    @GeneratedValue
    private Long id;
    private String galleryTitle;
    private String shortBio;
    private String tags;
    private boolean isPublic;
}
