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
@Table(name = "photogalleries")
public class PhotoGallery {

    @Id
    @GeneratedValue
    private Long id;
    private String photographerName;
    private String shortBio;
    private boolean publish;

    @OneToOne(mappedBy = "photoGallery")
    private User user;

    @OneToMany(mappedBy = "photoGallery")
    @JsonIgnore
    private List<Photo> photos;

    @ManyToOne
    @JoinColumn(name = "explore_user_gallery_id")
    private ExploreGallery exploreGallery;
}

