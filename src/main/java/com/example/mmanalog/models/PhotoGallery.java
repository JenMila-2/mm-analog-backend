package com.example.mmanalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;
    private String photographerName;
    private String shortBio;
    private String tag;
    private boolean isPublic;

    @OneToOne(mappedBy = "photoGallery")
    User user;

    @OneToMany(mappedBy = "photoGallery")
    @JsonIgnore
    List<Photo> photos;

    @ManyToOne
    @JoinColumn(name = "user_galleries_id")
    private ExploreGallery exploreGallery;
}
