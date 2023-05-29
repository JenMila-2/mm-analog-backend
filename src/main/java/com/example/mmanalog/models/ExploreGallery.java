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
@Table(name = "exploregalleries")
public class ExploreGallery {

    @Id
    @GeneratedValue
    private Long id;
    private String photographerName;
    private String photoTitle;
    private String imageURL;

    @OneToMany(mappedBy = "exploreGallery")
    @JsonIgnore
    private List<PhotoGallery> photoGalleries;
}
