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
@Table(name = "exploregalleries")
public class ExploreGallery {

    @Id
    @GeneratedValue
    private Long photoId;
    private String photographerName;
    private String photoTitle;
    private String imageURL;
}
