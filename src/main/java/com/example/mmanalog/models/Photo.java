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
}