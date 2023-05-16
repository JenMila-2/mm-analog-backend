package com.example.mmanalog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    private Long photoId;
    private String photographerName;
    private String shortBio;
    private String tag;
    private boolean isPublic;
}
